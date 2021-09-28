package Classes;

public class Products {
    private int id;
    private String p_name;
    private String ctg;
    private String u_cost;
    private int qnt;

    public Products(int id, String p_name, String ctg, String u_cost, int qnt) {
        this.id = id;
        this.p_name = p_name;
        this.ctg = ctg;
        this.u_cost = u_cost;
        this.qnt = qnt;
    }


    public int getId() {
        return id;
    }

    public String getP_name() {
        return p_name;
    }

    public String getCtg() {
        return ctg;
    }

    public String getU_cost() {
        return u_cost;
    }

    public int getQnt() {
        return qnt;
    }


}
