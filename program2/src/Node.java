class Node{
    public String key;
    public int frequency, probe , height;

    public Node(String key) {
        this.key = key;
        frequency = 1;
        probe = 0;
    }
    
    public static Node clone(Node node){
        if(node == null) return null;
        Node ret = new Node("");
        ret.assign(node);
        return ret;
    }

    public String toString(){
        return String.format("[%s:%d:%d] %d", key, frequency, probe, height);
    }

    public void assign(Node node){
        key = node.key;
        frequency = node.frequency;
        probe = node.probe;
        height = node.height;
    }
}