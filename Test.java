package Lab05;

public class Test {

	public static void main(String[] args) {
        Avl tree = new Avl();

        tree.root = tree.insert(tree.root, 65);  
        tree.root = tree.insert(tree.root, 66);  
        tree.root = tree.insert(tree.root, 67);  
        tree.root = tree.insert(tree.root, 68);  
        tree.root = tree.insert(tree.root, 69);  
        tree.root = tree.insert(tree.root, 70);  
        tree.root = tree.insert(tree.root, 71); 
        tree.root = tree.insert(tree.root, 50);  
        tree.root = tree.insert(tree.root, 75);  
        tree.root = tree.insert(tree.root, 80);  

        System.out.print("Árbol AVL en pre-orden: ");
        tree.preOrden(tree.root);
        System.out.println();

        NodeAvl searchNode = tree.search(tree.root, 69); 
        if (searchNode != null)
            System.out.println("Nodo encontrado: " + searchNode.key);
        else
            System.out.println("Nodo no encontrado");

        int min = tree.getMin(tree.root);
        System.out.println("Mínimo valor: " + min);

        int max = tree.getMax(tree.root);
        System.out.println("Máximo valor: " + max);

        NodeAvl parentNode = tree.parent(tree.root, 69);  
        if (parentNode != null)
            System.out.println("Padre: " + parentNode.key);
        else
            System.out.println("No tiene padre");

        boolean hasChildren = tree.son(tree.root, 66); 
        System.out.println("¿tiene hijos? " + hasChildren);

        tree.root = tree.deleteNode(tree.root, 68); 
        System.out.print("Árbol AVL después de eliminar el nodo  en pre-orden: ");
        tree.preOrden(tree.root);
        System.out.println();
    }
}
