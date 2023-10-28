/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package erp.sistema;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Walter Rosales
 */
public class FormPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root"); // Nodo raíz
    DefaultTreeModel treeModel = new DefaultTreeModel(root);
    JTree menu = new JTree(treeModel);
    
    // Agrega un TreeSelectionListener al JTree


    public FormPrincipal() {
        super("Sistema ERP pagina principal");
        
         // Establecer las dimensiones de la ventana
        this.setSize(1200, 800);
        // JFrame frame = new JFrame("Menu principal");
        // Establecer la operación al cerrar la ventana
      
        this.setExtendedState(JFrame.MAXIMIZED_HORIZ);
        // Hacer visible la ventana
        this.add(new JScrollPane(menu){
        
            
        
        }); // Agrega el árbol en un JScrollPane para desplazamiento
        this.pack();
        this.setVisible(true);  
        DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode("Herramientas del sistema"); // Nodo padre
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode("Usuarios"); // Nodo hijo
        DefaultMutableTreeNode compras   = new DefaultMutableTreeNode("Compras");
        DefaultMutableTreeNode ventas    = new DefaultMutableTreeNode("Ventas");
        DefaultMutableTreeNode childVenta = new DefaultMutableTreeNode("Facturación");
        DefaultMutableTreeNode childCompra = new DefaultMutableTreeNode("Registrar compra");
        DefaultMutableTreeNode inventario = new DefaultMutableTreeNode("Inventarios"); // Nodo padre
        DefaultMutableTreeNode childProducto = new DefaultMutableTreeNode("Productos"); // 
        DefaultMutableTreeNode childProveedor = new DefaultMutableTreeNode("Proveedores"); //Nodo hijo
        DefaultMutableTreeNode childClientes = new DefaultMutableTreeNode("Clientes");
        DefaultMutableTreeNode childCategoria = new DefaultMutableTreeNode("Categoria"); // Nodo hijo
        DefaultMutableTreeNode childProd = new DefaultMutableTreeNode("Producto");
        DefaultMutableTreeNode childIngresos = new DefaultMutableTreeNode("Ingresos productos");
        DefaultMutableTreeNode childAlmacenes = new DefaultMutableTreeNode("Almacenes");
        root.add(parentNode); // Agrega el nodo padre al nodo raíz
        parentNode.add(childNode); // Agrega el nodo hijo al nodo padre
        root.add(inventario);
        root.add(compras);
        root.add(ventas);
        
        inventario.add(childProducto);
        childProducto.add(childCategoria);
        childProducto.add(childProd);
        inventario.add(childIngresos);
        inventario.add(childAlmacenes);
        ventas.add(childVenta);
        compras.add(childCompra);
        compras.add(childProveedor);
        ventas.add(childClientes);

        // Actualiza el modelo del árbol para reflejar los cambios
        treeModel.reload();
        initComponents();
       
        // Agrega un TreeSelectionListener al JTree
        menu.addTreeSelectionListener(new TreeSelectionListener() {
         @Override
         public void valueChanged(TreeSelectionEvent e) {
              DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) menu.getLastSelectedPathComponent();

                if (selectedNode != null) {
                 // Verifica si el nodo seleccionado es igual al nodo "Usuarios"
                 if (selectedNode.toString().equals("Usuarios")) {
                     // Haz algo cuando se haga clic en el nodo "Usuarios"
                       // JOptionPane.showMessageDialog(null, "Haz clic en el nodo 'Usuarios'");
                       FormUsuarios nuevoU = new FormUsuarios();
                       nuevoU.setVisible(true);
                       
                     }
                 if (selectedNode.toString().equals("Categoria")) {
                     // Haz algo cuando se haga clic en el nodo "Usuarios"
                       // JOptionPane.showMessageDialog(null, "Haz clic en el nodo 'Usuarios'");
                       FormCategoria cate = new FormCategoria();
                       cate.setVisible(true);
                       
                     }
                  if (selectedNode.toString().equals("Producto")) {
                     // Haz algo cuando se haga clic en el nodo "Producto"
                       // JOptionPane.showMessageDialog(null, "Haz clic en el nodo 'Usuarios'");
                       FormProductos cate = new FormProductos();
                       cate.setVisible(true);
                       
                     }
                  if (selectedNode.toString().equals("Almacenes")) {
                     // Haz algo cuando se haga clic en el nodo "Almacenes"
                       // JOptionPane.showMessageDialog(null, "Haz clic en el nodo 'Almacenes'");
                       FormAlmacen alm = new FormAlmacen();
                       alm.setVisible(true);
                       
                     }
                  if (selectedNode.toString().equals("Compras")) {
                     // Haz algo cuando se haga clic en el nodo "Compras"
                       // JOptionPane.showMessageDialog(null, "Haz clic en el nodo 'Compras'");
                       FormCompra comp = new FormCompra();
                       comp.setVisible(true);
                       
                     }
                   if (selectedNode.toString().equals("Proveedores")) {
                     // Haz algo cuando se haga clic en el nodo "Proveedores"
                       // JOptionPane.showMessageDialog(null, "Haz clic en el nodo 'Compras'");
                       FromProveedor pro = new FromProveedor();
                       pro.setVisible(true);
                       
                     }
                   if (selectedNode.toString().equals("Clientes")) {
                     // Haz algo cuando se haga clic en el nodo "Clientes"
                       // JOptionPane.showMessageDialog(null, "Haz clic en el nodo 'Compras'");
                       FormCliente pro = new FormCliente();
                       pro.setVisible(true);
                       
                     }
                 
                 }
             }
        });

        
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Principal");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 704, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPrincipal().setVisible(true);
            }
        });
        
        
        
        
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
