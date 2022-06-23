package br.com.iris.control;

import br.com.iris.dao.PlanoDao;
import br.com.iris.model.Plano;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.RollbackException;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;


@ManagedBean(name = "planoMB")
@ViewScoped
public class PlanoMB implements Serializable{
    private TreeNode planoTree;
    private List<Plano> plano;
    private TreeNode selectedNode;
    private Plano conta=new Plano();
    private Plano contaPai=new Plano();
    private TreeNode contaPaiTree;
    private PlanoDao dao=new PlanoDao();

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
        if (selectedNode!=null){
            conta=(Plano)selectedNode.getData();
        }else{
            conta=new Plano();
        }
    }

    public TreeNode getPlano() {
        if (planoTree==null){
            refresh();
        }
        return planoTree;
    }

    private void refresh() {
        plano=dao.getAll();
        planoTree=new DefaultTreeNode("Plano de contas", null);
        addContas(plano, planoTree);
    }
    
    private void addContas(List<Plano>contas, TreeNode no){
        Collections.sort(contas, (Plano o1, Plano o2) -> o1.getOrdem()-o2.getOrdem());
        for (Plano p:contas){
            if (p.isMovimentacao()){
                no.getChildren().add(new DefaultTreeNode("movto",p,no));
                no.setExpanded(true);
            }else{
                TreeNode novo=new DefaultTreeNode(p,no);
                addContas(p.getFilhos(), novo);
                no.getChildren().add(novo);
                no.setExpanded(true);
            }
        }
    }

    public Plano getConta() {
        return conta;
    }

    public void setConta(Plano conta) {
        this.conta = conta;
    }

    public void novo(){
        conta=new Plano();
    }
    
    public void gravar(){
        if (conta!=null){
            if (conta.getId()!=0 && conta.isMovimentacao() && conta.getFilhos()!=null && !conta.getFilhos().isEmpty()){
                FacesContext.getCurrentInstance().addMessage("",new FacesMessage("Esta conta possui sub-contas. Não é possível torná-la uma conta de movimentação."));
                return;
            }
            
            //estabelece ordem para organizar as contas dentro do grau (organiza os filhos dentrop da conta pai)
            if (conta.getOrdem()==0){
                if (conta.getPai()!=null){
                    if (conta.getPai().getFilhos().isEmpty()){
                        conta.setOrdem(1);
                    }else{
                        conta.setOrdem(conta.getPai().getFilhos().get(conta.getPai().getFilhos().size()-1).getOrdem()+1);
                    }
                }else{
                    conta.setMovimentacao(false);//Forçar ele ser falso em caso de não ter conta pai.
                    if (plano!=null && !plano.isEmpty()){
                        conta.setOrdem(plano.get(plano.size()-1).getOrdem()+1);
                    }else{
                        conta.setOrdem(1);
                    }
                }
            }
            Plano contaGravada=dao.save(conta);
            refresh();
        }
        novo();
    }
    
    public void excluir(String dialog){
        if (conta!=null && conta.getId()!=0){
            try{
                dao.delete(Plano.class, conta.getId());
                PrimeFaces.current().executeScript("PF('"+dialog+"').hide()");
            }catch(RollbackException e){
                FacesContext.getCurrentInstance().addMessage("",new FacesMessage("Esta conta está em uso. Não é possível excluí-la."));
                return;
            }
            refresh();
        }
        novo();
    }

    public Plano getContaPai() {
        return contaPai;
    }

    public void setContaPai(Plano contaPai) {
        this.contaPai = contaPai;
        conta.setPai(contaPai);
    }
    
    public void apagaPai(){
        setContaPai(null);
    }
    
    public void inserirSubConta(){
        Plano p=conta;
        novo();
        if (p.isMovimentacao()){
            setContaPai(p.getPai());
        }else{
            setContaPai(p);
        }
    }
    
    public void sobeNivel(Plano conta){
        setConta(conta);
        trocaNivel(true);
    }
    public void desceNivel(Plano conta){
        setConta(conta);
        trocaNivel(false);
    }
    public void trocaNivel(boolean acima){
        if (acima && conta.getOrdem()==1){return;}

        Plano ct1=null;
        Plano ct2=null;

        List<Plano> cts=null;
        if (conta.getPai()==null){
            cts=plano;
        }else{
            cts=conta.getPai().getFilhos();
        }

        if (acima) {
            for (Plano p : cts) {
                if (p.equals(conta)) {
                    ct2 = p;
                    if (ct1!=null) {
                        int o1 = ct1.getOrdem();
                        int o2 = ct2.getOrdem();
                        ct2.setOrdem(o1);
                        ct1.setOrdem(o2);
                    }
                    break;
                } else {
                    ct1 = p;
                }
            }
        }else{
            for (Plano p : cts) {
                if (p.equals(conta)) {
                    ct2 = p;
                } else {
                    ct1 = p;
                    if (ct2!=null) {
                        int o1 = ct1.getOrdem();
                        int o2 = ct2.getOrdem();
                        ct2.setOrdem(o1);
                        ct1.setOrdem(o2);
                        break;
                    }
                }
            }
        }
        planoTree=new DefaultTreeNode("Plano de contas", null);
        addContas(plano, planoTree);
    }

    public TreeNode getContaPaiTree() {
        return contaPaiTree;
    }

    public void setContaPaiTree(TreeNode contaPaiTree) {
        this.contaPaiTree = contaPaiTree;
        if (contaPaiTree != null && contaPaiTree.getData() instanceof Plano){
            setContaPai((Plano)contaPaiTree.getData());
            if (getContaPai().isMovimentacao()){
               apagaPai();
               FacesContext.getCurrentInstance().addMessage("",new FacesMessage("A conta selecionada é uma conta de movimentação."));
               return;
            }
        }
    }

}
