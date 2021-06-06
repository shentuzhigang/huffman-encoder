package huffman;

public class HTNode implements Comparable<HTNode> {
	int id;
	double weight;
	char code;
	int leaf;
	int parent,lchild,rchild;
	public HTNode(int id) {
		this.id=id;
		this.weight=-1;
		this.code='\0';
		this.parent=-1;
		this.lchild=-1;
		this.rchild=-1;
		this.leaf=1;
	}
	public HTNode(int id,double weight) {
		this.id=id;
		this.weight=weight;
		this.code='\0';
		this.parent=-1;
		this.lchild=-1;
		this.rchild=-1;
		this.leaf=1;
	}
	public HTNode(int id,char code,double weight) {
		this.id=id;
		this.weight=weight;
		this.code=code;
		this.parent=-1;
		this.lchild=-1;
		this.rchild=-1;
		this.leaf=1;
	}
	public HTNode(int id,char code,double weight,int lchild,int rchild) {
		this.id=id;
		this.weight=weight;
		this.code=code;
		this.parent=-1;
		this.lchild=lchild;
		this.rchild=rchild;
		this.leaf=1;
	}
	public HTNode(int id,char code,double weight,int parent,int lchild,int rchild) {
		this.id=id;
		this.weight=weight;
		this.code=code;
		this.parent=parent;
		this.lchild=lchild;
		this.rchild=rchild;
		this.leaf=1;
	}
	public HTNode(int id,char code,double weight,int parent,int lchild,int rchild,int leaf) {
		this.id=id;
		this.weight=weight;
		this.code=code;
		this.parent=parent;
		this.lchild=lchild;
		this.rchild=rchild;
		this.leaf=leaf;
	}
	public HTNode(int id,HTNode lchild,HTNode rchild) {
		this.id=id;
		this.weight=lchild.weight+rchild.weight;
		this.code='\0';
		this.parent=-1;
		this.lchild=lchild.id;
		this.rchild=rchild.id;
		this.leaf=lchild.leaf+rchild.leaf+1;
	}
    public int compareTo(HTNode x) {
        if (x.weight < this.weight) {
            return 1;
        } else if (x.weight > this.weight) {
            return -1;
        }else {
        	 if (x.leaf < this.leaf) {
                 return 1;
             } else if (x.leaf > this.leaf) {
                 return -1;
             }
        }
        return 0;
    }
}
