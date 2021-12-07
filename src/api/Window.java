package api;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window implements ActionListener {
    private JFrame mainFrame,subFrame;
    private JMenuBar menuBar;
    JMenu File,Edit, algo;
    JMenuItem load, save;
    JMenuItem addNode, connectNodes,removeNode,removeEdge;
    JMenuItem isConnect, shortestPathDist,center,tsp;
    JTextField loadTextField, saveTextField, addNodeTextField, connectNodesTextField, removeNodeTextField,removeEdgeTextField,shortestPathDistTextField;
    DirectedWeightedGraphAlgorithms dwga;
    Container c = new Container();
    int counterOfSaves;

    public static void main(String[] args) {
        Window w = new Window();

    }

    public Window(){
        dwga = new MyDirectedWeightedGraphAlgorithms();
        counterOfSaves=0;

        mainFrame= new JFrame("Graph Drawer");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500,500);
        mainFrame.setLocationRelativeTo(null);
        menuBar=new JMenuBar();

        File= new JMenu("File");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        load.addActionListener(this);
        save.addActionListener(this);
        File.add(load);
        File.add(save);
        menuBar.add(File);

        Edit= new JMenu("Edit");
        addNode = new JMenuItem("Add Node");
        connectNodes = new JMenuItem("Connect Nodes");
        removeNode = new JMenuItem("Remove Node");
        removeEdge = new JMenuItem("Remove Edge");
        addNode.addActionListener(this);

        connectNodes.addActionListener(this);
        removeNode.addActionListener(this);
        removeEdge.addActionListener(this);
        Edit.add(addNode);
        Edit.add(connectNodes);
        Edit.add(removeNode);
        Edit.add(removeEdge);
        menuBar.add(Edit);

        algo= new JMenu("Algorithms");
        isConnect = new JMenuItem("Is The Graph Connected?");
        shortestPathDist = new JMenuItem("The shortest path between:");
        center = new JMenuItem("Center");
        tsp = new JMenuItem("tsp");
        isConnect.addActionListener(this);
        shortestPathDist.addActionListener(this);
        center.addActionListener(this);
        tsp.addActionListener(this);
        algo.add(isConnect);
        algo.add(shortestPathDist);
        algo.add(center);
        algo.add(tsp);
        menuBar.add(algo);

        mainFrame.setJMenuBar(menuBar);
        mainFrame.setLayout(null);
        File.setVisible(true);
        mainFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==load) {
            subFrame = getNewFrame();

            loadTextField = getNewTextField();
            loadTextField.addActionListener(this);
            subFrame.add(loadTextField);

            JLabel filePath = new JLabel("Enter file path: ");
            subFrame.add(filePath);

            filePath.setVisible(true);
            loadTextField.setVisible(true);
            subFrame.setVisible(true);
        }
        if (e.getSource()== loadTextField){
            dwga.load(new String(loadTextField.getText()));
            System.out.println("Graph loaded");
            subFrame.setVisible(false);
        }

     if(e.getSource()==save) {
         subFrame =getNewFrame();

         saveTextField = getNewTextField();
         saveTextField.addActionListener(this);
         subFrame.add(saveTextField);

         JLabel fileName = new JLabel("Enter file name: ");
         subFrame.add(fileName);

         fileName.setVisible(true);
         saveTextField.setVisible(true);
         subFrame.setVisible(true);
     }
     if (e.getSource()==saveTextField){
         dwga.save(new String(saveTextField.getText()));
         System.out.println("saving this graph");
         subFrame.setVisible(false);
     }

        if(e.getSource()==addNode) {
            subFrame =getNewFrame();

            addNodeTextField = getNewTextField();
            addNodeTextField.addActionListener(this);
            subFrame.add(addNodeTextField);

            JLabel fileName = new JLabel("Enter Node Location: ");
            subFrame.add(fileName);

            fileName.setVisible(true);
            addNodeTextField.setVisible(true);
            subFrame.setVisible(true);
        }
        if (e.getSource()==addNodeTextField){
            NodeData nd = new Node(addNodeTextField.getText(),dwga.getGraph().nodeSize());
            dwga.getGraph().addNode(nd);
            System.out.println("Adding Node");
            subFrame.setVisible(false);
        }
        if(e.getSource()==connectNodes) {
            subFrame =getNewFrame();

            connectNodesTextField = getNewTextField();
            connectNodesTextField.addActionListener(this);
            subFrame.add(connectNodesTextField);

            JLabel fileName = new JLabel("Enter: srcId,destId,wight");
            subFrame.add(fileName);

            fileName.setVisible(true);
            connectNodesTextField.setVisible(true);
            subFrame.setVisible(true);
        }
        if (e.getSource()==connectNodesTextField){
            String[] arrOfStr = connectNodesTextField.getText().split(",");

            dwga.getGraph().connect(Integer.parseInt(arrOfStr[0]),Integer.parseInt(arrOfStr[1]),Double.parseDouble(arrOfStr[2]));
            System.out.println("Adding Edge");
            subFrame.setVisible(false);
        }
        if(e.getSource()==removeNode) {
            subFrame =getNewFrame();

            removeNodeTextField = getNewTextField();
            removeNodeTextField.addActionListener(this);
            subFrame.add(removeNodeTextField);

            JLabel fileName = new JLabel("Enter the id of Node: ");
            subFrame.add(fileName);

            fileName.setVisible(true);
            removeNodeTextField.setVisible(true);
            subFrame.setVisible(true);
        }
        if (e.getSource()==removeNodeTextField){
            int id =Integer.parseInt(removeNodeTextField.getText());
            dwga.getGraph().removeNode(id);
            System.out.println("Removing Node");
            subFrame.setVisible(false);
        }

        if(e.getSource()==removeEdge) {
            subFrame =getNewFrame();

            removeEdgeTextField = getNewTextField();
            removeEdgeTextField.addActionListener(this);
            subFrame.add(removeEdgeTextField);

            JLabel fileName = new JLabel("Enter: srcId, destId");
            subFrame.add(fileName);

            fileName.setVisible(true);
            removeEdgeTextField.setVisible(true);
            subFrame.setVisible(true);
        }
        if (e.getSource()==removeEdgeTextField){
            String[] arrOfStr = removeEdgeTextField.getText().split(",");

            dwga.getGraph().removeEdge(Integer.parseInt(arrOfStr[0]),Integer.parseInt(arrOfStr[1]));
            System.out.println("Removing Edge");
            subFrame.setVisible(false);
        }

        if(e.getSource()==isConnect) {
            subFrame =getNewFrame();

            boolean connected = dwga.isConnected();
            if (connected) JOptionPane.showMessageDialog(mainFrame, "The Graph Is Connected");
            else JOptionPane.showMessageDialog(mainFrame, "The Graph Is Not Connected");
            System.out.println("is Connected: " +connected);

        }

        if(e.getSource()==shortestPathDist) {
            subFrame =getNewFrame();

            shortestPathDistTextField = getNewTextField();
            shortestPathDistTextField.addActionListener(this);
            subFrame.add(shortestPathDistTextField);

            JLabel fileName = new JLabel("Enter: srcId, destId");
            subFrame.add(fileName);

            fileName.setVisible(true);
            shortestPathDistTextField.setVisible(true);
            subFrame.setVisible(true);
        }
        if (e.getSource()==shortestPathDistTextField){
            String[] arrOfStr = shortestPathDistTextField.getText().split(",");

            double a = dwga.shortestPathDist(Integer.parseInt(arrOfStr[0]),Integer.parseInt(arrOfStr[1]));
            if (a==-1) JOptionPane.showMessageDialog(mainFrame, "There is no path.");
            else JOptionPane.showMessageDialog(mainFrame, "The shortest Path Distance is: "+ a);
            System.out.println("The shortest Path Distance is: "+ a);
            subFrame.setVisible(false);
        }
    }

    private JFrame getNewFrame(){
        JFrame newFrame = new JFrame("Enter Details");
        newFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        newFrame.setSize(250,250);
        newFrame.setLocationRelativeTo(null);
        return newFrame;
    }
    private JTextField getNewTextField(){
        JTextField txf = new JTextField();
        txf.setSize(250,100);
        return txf;
    }
}
