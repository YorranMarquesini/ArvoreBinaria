package Class;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left, right;

    Node(int value) {
        data = value;
        left = right = null;
    }
}

public class Main {
    Node root;

 // Inserção
    public Node insert(Node node, int value) {
        if (node == null) return new Node(value);
        if (value < node.data)
            node.left = insert(node.left, value);
        else if (value > node.data)
            node.right = insert(node.right, value);
        return node;
    }

 // Remoção
    public Node remove(Node node, int value) {
        if (node == null) return null;

        if (value < node.data)
            node.left = remove(node.left, value);
        else if (value > node.data)
            node.right = remove(node.right, value);
        else {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;

            node.data = findMin(node.right).data;
            node.right = remove(node.right, node.data);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }
    
    // Impressões
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }
    
 // Grau de um nó
    public int grau(Node node) {
        int g = 0;
        if (node.left != null) g++;
        if (node.right != null) g++;
        return g;
    }
 
    // Grau de um valor específico
    public int grauPorValor(Node node, int valor) {
        if (node == null) return -1;
        if (node.data == valor) return grau(node);
        if (valor < node.data)
            return grauPorValor(node.left, valor);
        else
            return grauPorValor(node.right, valor);
    }
    
 // Buscar um elemento
    public boolean busca(Node node, int valor) {
        if (node == null) return false;
        if (node.data == valor) return true;
        if (valor < node.data)
            return busca(node.left, valor);
        else
            return busca(node.right, valor);
    }

 // Altura da árvore
    public int altura(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(altura(node.left), altura(node.right));
    }
    
 // Verifica se é estritamente binária (0 ou 2 filhos)
    public boolean isEstritamenteBinaria(Node node) {
        if (node == null) return true;
        if ((node.left == null && node.right != null) || (node.left != null && node.right == null))
            return false;
        return isEstritamenteBinaria(node.left) && isEstritamenteBinaria(node.right);
    }
    
 // Verifica se é cheia
    public boolean isCheia(Node node) {
        if (node == null) return true;
        if (node.left == null && node.right == null) return true;
        if (node.left != null && node.right != null)
            return isCheia(node.left) && isCheia(node.right);
        return false;
    }
    
 // Verifica se é completa
    public boolean isCompleta(Node root) {
        if (root == null) return true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean encontrouFolha = false;

        while (!queue.isEmpty()) {
            Node atual = queue.poll();
            if (atual.left != null) {
                if (encontrouFolha) return false;
                queue.add(atual.left);
            } else {
                encontrouFolha = true;
            }

            if (atual.right != null) {
                if (encontrouFolha) return false;
                queue.add(atual.right);
            } else {
                encontrouFolha = true;
            }
        }
        return true;
    }

    // Menu
    public static void main(String[] args) {
        Main arvore = new Main();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU ÁRVORE BINÁRIA ---");
            System.out.println("1. Inserir elemento");
            System.out.println("2. Remover elemento");
            System.out.println("3. Buscar elemento");
            System.out.println("4. Exibir em-ordem");
            System.out.println("5. Exibir pré-ordem");
            System.out.println("6. Exibir pós-ordem");
            System.out.println("7. Altura da árvore");
            System.out.println("8. Grau de um nó");
            System.out.println("9. A árvore é estritamente binária?");
            System.out.println("10. A árvore é cheia?");
            System.out.println("11. A árvore é completa?");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor a inserir: ");
                    int valorIns = scanner.nextInt();
                    arvore.root = arvore.insert(arvore.root, valorIns);
                    break;
                case 2:
                    System.out.print("Digite o valor a remover: ");
                    int valorRem = scanner.nextInt();
                    arvore.root = arvore.remove(arvore.root, valorRem);
                    break;
                case 3:
                    System.out.print("Digite o valor a buscar: ");
                    int valorBusca = scanner.nextInt();
                    System.out.println(arvore.busca(arvore.root, valorBusca)
                        ? "Elemento encontrado."
                        : "Elemento não encontrado.");
                    break;
                case 4:
                    System.out.print("Em-ordem: ");
                    arvore.inOrder(arvore.root);
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Pré-ordem: ");
                    arvore.preOrder(arvore.root);
                    System.out.println();
                    break;
                case 6:
                    System.out.print("Pós-ordem: ");
                    arvore.postOrder(arvore.root);
                    System.out.println();
                    break;
                case 7:
                    System.out.println("Altura da árvore: " + arvore.altura(arvore.root));
                    break;
                case 8:
                    System.out.print("Digite o valor do nó: ");
                    int valorGrau = scanner.nextInt();
                    int grau = arvore.grauPorValor(arvore.root, valorGrau);
                    if (grau == -1)
                        System.out.println("Nó não encontrado.");
                    else
                        System.out.println("Grau do nó " + valorGrau + ": " + grau);
                    break;
                case 9:
                    System.out.println("Estritamente binária? " +
                            (arvore.isEstritamenteBinaria(arvore.root) ? "Sim" : "Não"));
                    break;
                case 10:
                    System.out.println("Cheia? " +
                            (arvore.isCheia(arvore.root) ? "Sim" : "Não"));
                    break;
                case 11:
                    System.out.println("Completa? " +
                            (arvore.isCompleta(arvore.root) ? "Sim" : "Não"));
                    break;
                case 0:
                    System.out.println("Por ho ho hoje é s s s s só pe pe pe pessoal 🐷...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
