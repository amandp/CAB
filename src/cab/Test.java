/*
 * Selectable.java
 *
 * Created on December 2, 2003, 11:22 PM
 */

/**
 *
 * @author  linyuan
 */
public class Selectable {
    
    static Selectable selected = null;
    
    static java.awt.Color selectedColor = java.awt.Color.blue;
    
    java.awt.Color color = java.awt.Color.black;
    
    public final boolean isSelected(){
        return equals(selected);
    };
    
    public final static void select(Selectable s){
        selected=s;
    };
    
    public final static void unselect(){
        selected=null;
    };
    
    double distance(int x, int y){return 10000;};
    
}
/*
 * label.java
 *
 * Created on February 27, 2004, 10:08 AM
 */

/**
 *
 * @author  linyuan
 */
public class Label extends Selectable {
    
    String label;
    int x;
    int y;
    private Selectable labelBase;
    private java.awt.Color color = java.awt.Color.black;
    
    
     // The font to use for this label.
    private java.awt.Font labelFont = new java.awt.Font("TimesRoman",java.awt.Font.PLAIN,14);
    // Metrics for labelFont.
    private java.awt.FontMetrics labelFontMetrics =
    java.awt.Toolkit.getDefaultToolkit().getFontMetrics(labelFont);
                                                                                
    // Make sure labelFontMetrics change whenever labelFont changes.
    public final void setFont(java.awt.Font f) {
        labelFont = f;
        labelFontMetrics = java.awt.Toolkit.getDefaultToolkit().getFontMetrics(f);
    }
    
    public int getWidth(){
        return labelFontMetrics.stringWidth(label);
    }
    
    public int getHeight(){
        return labelFontMetrics.getAscent() + labelFontMetrics.getDescent();
    }
    public final  void moveLabel(int x,int y) {
      this.x = x;
      this.y = y - labelFontMetrics.getAscent();
     }
 
    // Shift label by x,y pixels.
    public final void moveLabelRelative(int x,int y) {
        if (labelBase==null) moveLabel(x,y);
        else moveLabel(x+this.x,y+this.y);
    }
    
    public final void extendLabel(char c) {
        if (Character.isISOControl(c)) {
            return;
        }
        if (label==null) label = "";
        label += c;
     }

    public final void shortenLabel() {
        if ((label!=null) && (label.length()>1)) {
          label = label.substring(0,label.length()-1);
                                                                                
        } else label = "";
    }

      // Display the label.
      public final void draw(java.awt.Graphics g) {
        if (label==null) return;
        
        if ("".equals(label)) return;
        if(isSelected()) g.setColor(selectedColor);
        else g.setColor(color);
        g.setFont(labelFont);
        g.drawString(label,x,y+labelFontMetrics.getDescent());
  }

 
    public final java.awt.Font getFont() { return labelFont; }

    
    /** Creates a new instance of label */
    public Label() {
    }
    
    public Label(int x, int y, String s){
        this.x=x;
        this.y=y;
        label=s;
    }
    
}
/*
 * Vertex.java
 *
 * Created on December 2, 2003, 11:37 PM
 */

/**
 *
 * @author  linyuan
 */
public class Vertex extends Selectable {
    
    int x;
    
    int y;
    
    int r;
    
    java.util.List inedges = null;
    java.util.List outedges = null;
    
    /** Creates a new instance of Vertex */
    public Vertex() {
    }
    
    double distance(int x, int y) {
        double temp=Math.sqrt(0.0+(this.x-x)*(this.x-x)+(this.y-y)*(this.y-y));
        if(temp<=this.r) return 0;
        else return temp;
    }
    

    public Vertex(int x, int y) {
        this.x=x;
        this.y=y;
        this.r=5;
        inedges=new java.util.LinkedList();
	outedges=new java.util.LinkedList();
    }
    
    public void draw(java.awt.Graphics g) {
        if(isSelected()) g.setColor(selectedColor);
        else g.setColor(color);
        g.fillOval(x-r,y-r,2*r,2*r);
    }
   
    
}
/*
 * Edge.java
 *
 * Created on December 3, 2003, 12:49 AM
 */

/**
 *
 * @author  linyuan
 */
public class Edge extends Selectable {
    
    Vertex u;
    
    Vertex v;
    
    /** Creates a new instance of Edge */
    public Edge() {
    }
    
    public Edge(Vertex u, Vertex v){
     this.u=u;
     this.v=v;
    }
    double distance(int x, int y) {
        double a, b, c;
        a=(u.x-x)*(u.x-x)+(u.y-y)*(u.y-y)+0.0;
        b=(v.x-x)*(v.x-x)+(v.y-y)*(v.y-y)+0.0;
        c=(u.x-v.x)*(u.x-v.x)+(u.y-v.y)*(u.y-v.y)+0.0;
        if(a>=b+c) return Math.sqrt((double) b);
        if(b>=a+c) return Math.sqrt((double) a);
        return Math.sqrt(b-(c+b-a)*(c+b-a)/4.0/c);
    }
    
    public void draw(java.awt.Graphics g){
        if(isSelected()) g.setColor(selectedColor);
        else g.setColor(color);
        g.drawLine(u.x,u.y,v.x,v.y);

	//draw end arrow
	int r = 3; //thickness of vertex
	int s = 5; //thickness of line
	double ax = u.x+0.5;
	double ay = u.y+0.5;
	double length=Math.sqrt((v.x-u.x)*(v.x-u.x)+(v.y-u.y)*(v.y-u.y));
	if (length==0) return;
	double sin = (v.y-u.y)/length;
	double cos = (v.x-u.x)/length ;
	double px = length-(r+1);
	double py = 0;
	double ly = s;
	double lx = length-(r+s+s);
	double rx = lx;
	double ry = -ly;
	double mx = length-(r+s+s/2);
	double my = 0;
	double tx; double ty;
	tx = px*cos-py*sin;
	ty = px*sin+py*cos;
	px = tx; py = ty;
	tx = lx*cos-ly*sin;
	ty = lx*sin+ly*cos;
	lx = tx; ly = ty;
	tx = rx*cos-ry*sin;
	ty = rx*sin+ry*cos;
	rx = tx; ry = ty;
	tx = mx*cos-my*sin;
	ty = mx*sin+my*cos;
	mx = tx; my = ty;
	px+=ax; py+=ay;
	mx+=ax; my+=ay;
	lx+=ax; ly+=ay;
	rx+=ax; ry+=ay;
	int[] xvals=new int[4];
        int[] yvals=new int[4];
	xvals[0]=(int)px;
	xvals[1]=(int)lx;
	xvals[2]=(int)mx;
	xvals[3]=(int)rx;
	yvals[0]=(int)py;
	yvals[1]=(int)ly;
	yvals[2]=(int)my;
	yvals[3]=(int)ry;
	g.fillPolygon(xvals,yvals,4);
    }
}

/*
 * GraphPanel.java
 *
 * Created on December 3, 2003, 11:22 PM
 */

/**
 *
 * @author  linyuan
 */

/*
 * Test.java
 *
 * Created on December 6, 2003, 2:58 AM
 */

/**
 *
 * @author  linyuan
 */
/*
 * Graph.java
 *
 * Created on December 3, 2003, 1:11 AM
 */

/**
 *
 * @author  linyuan
 */
public class Graph {
    
    private java.util.List vertices;
    
    private java.util.List edges;
    private java.util.List labels;
    private java.awt.Color edgeColor=null;
    private java.awt.Color vertexColor=null;
    
    /** Creates a new instance of Graph */
    public Graph() {
        vertices=new java.util.LinkedList();
        edges=new java.util.LinkedList();
        labels=new java.util.LinkedList();
    }
    
    void add(Label l){
        labels.add(l);
    }
    
    void add(Vertex u) {
        vertices.add(u);
    }
    
    void add(Edge e) {
        if(edgeColor!=null) e.color=edgeColor;
        edges.add(e);
        e.u.outedges.add(e);
        e.v.inedges.add(e);
    }
    
    public void draw(java.awt.Graphics g) {
       int n = edges.size();
       int j;
       for (j=0;j<n;j++)
       ((Edge)edges.get(j)).draw(g);
       n = vertices.size();
       for (j=0;j<n;j++)
      ((Vertex)vertices.get(j)).draw(g); 
       n = labels.size();
       for (j=0;j<n;j++)
      ((Label)labels.get(j)).draw(g); 
    }
    
    void delete(Vertex u) {
       int j;
       while(u.inedges.size()>0){
           delete((Edge) u.inedges.get(0));
       }
       while(u.outedges.size()>0){
           delete((Edge) u.outedges.get(0));
       }
       vertices.remove(u);
    }
    
    void delete(Edge e) {
        e.u.outedges.remove(e);
        e.v.inedges.remove(e);
        edges.remove(e);
    }
    
    public Edge hitEdge(int x, int y){
        int limit=8;
        Edge e;
        int n = edges.size();
        java.util.List hits=new java.util.LinkedList();
        
        for (int j=0;j<n;j++) {
        e = (Edge) edges.get(j);
        if (e.distance(x,y)<limit) hits.add(e);
        }
        if (hits.size()==0) return null;
        while (hits.size()>1) {
            hits.clear();
            limit--;
            for (int j=0;j<n;j++) {
                e = (Edge) edges.get(j);
                if (e.distance(x,y)<limit) hits.add(e);
            }
         }
         return (Edge)(hits.get(0));
    }

    public Vertex hitVertex(int x, int y){
        for(int i=0;i<vertices.size();i++){
            Vertex v=(Vertex) vertices.get(i);
            if(v.distance(x,y)<0.5) return v;
        }
        return null;
    }
    
     public Label hitLabel(int x, int y){
        for(int i=0;i<labels.size();i++){
            Label l=(Label) labels.get(i);
            if(x>=l.x && x<=l.x+l.getWidth() 
            && y>=l.y-l.getHeight() && y<=l.y)
                return l;
        }
        return null;
    }
    
    
    
    public int getN(){
        return vertices.size();
    }
    
    public int getE(){
        return edges.size();
    }
    
    public Vertex getVertex(int i){
        return (Vertex) vertices.get(i);
    }
    
    public void clear(){
        edges.clear();
        vertices.clear();
    }
    
    public void setDefaultEdgeColor(java.awt.Color color){
        edgeColor=color;
    }
    
               
}