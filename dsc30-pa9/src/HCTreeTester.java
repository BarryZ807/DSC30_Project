import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class HCTreeTester {

    /**
     * Tests encode() and decode().
     * @param tree HCTree to test
     * @param input the byte to reconstruct
     * @return whether the encode-decode can reconstruct the input byte
     * @throws IOException from stream
     */
    private static boolean testByte(HCTree tree, byte input) throws IOException {

        // build out-stream
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(byteOut);
        BitOutputStream bitOut = new BitOutputStream(dataOut);

        // encode byte
        tree.encode(input, bitOut);

        // send data from out-stream to in-stream
        bitOut.flush();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        DataInputStream dataIn = new DataInputStream(byteIn);
        BitInputStream bitIn = new BitInputStream(dataIn);

        // decode byte and compare with input
        boolean result = (input == tree.decode(bitIn));

        // close streams
        dataOut.close();
        byteOut.close();
        dataIn.close();
        byteIn.close();
        return result;
    }

    /**
     * Checks if `expected` and `actual` have the same structure,
     * regardless of the instance variables on the nodes.
     * @param expected the root of the expected tree
     * @param actual the root of the actual tree
     * @return whether they share the same structure
     */
    private static boolean sameTreeStructure(HCTree.HCNode expected, HCTree.HCNode actual) {
        if (expected == null && actual == null) return true;
        if (expected == null || actual == null) return false;
        return sameTreeStructure(expected.c0, actual.c0)
                && sameTreeStructure(expected.c1, actual.c1);
    }

    /**
     * Tests encodeHCTree() and decodeHCTree().
     * @param tree HCTree to test
     * @return whether the encode-decode can reconstruct the tree
     * @throws IOException from stream
     */
    private static boolean testTree(HCTree tree) throws IOException {
        // build out-stream
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(byteOut);
        BitOutputStream bitOut = new BitOutputStream(dataOut);

        // encode tree
        tree.encodeHCTree(tree.getRoot(), bitOut);

        // send data from out-stream to in-stream
        bitOut.flush();
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        DataInputStream dataIn = new DataInputStream(byteIn);
        BitInputStream bitIn = new BitInputStream(dataIn);

        // decode tree and compare with input
        HCTree treeOut = new HCTree();
        treeOut.setRoot(treeOut.decodeHCTree(bitIn));
        boolean result = sameTreeStructure(tree.getRoot(), treeOut.getRoot());

        // close streams
        dataOut.close();
        byteOut.close();
        dataIn.close();
        byteIn.close();
        return result;
    }

    @Test
    public void example1() throws IOException{
        // an hctree with a 17 and b 8
        int[] f = new int[256];
        for (int i = 0; i < f.length; i++) {
            if (i == 97) {
                f[i] = 17;
            } else if (i == 98) {
                f[i] = 8;
            } else {
                f[i] = 0;
            }
        }
        HCTree t = new HCTree();
        t.buildTree(f);
        
        byte byteForA = (byte) 'a';
        byte byteForB = (byte) 'b';
        //build example tree
        HCTree example = new HCTree();
        HCTree.HCNode root = example.new HCNode(byteForA, 25);
        HCTree.HCNode c0 = example.new HCNode(byteForA, 17);
        HCTree.HCNode c1 = example.new HCNode(byteForB, 8);
        root.setC0(c0); root.setC1(c1); c0.setParent(root);c1.setParent(root);example.setRoot(root);
        assertTrue(sameTreeStructure(root, t.getRoot()));

        assertTrue(testByte(t, byteForA));
        assertTrue(testByte(t, byteForB));
        assertTrue(testTree(t));
    }

    @Test
    public void example2() throws IOException{
        //build tree with freqs
        int[] fr = new int[255];
        for (int i = 0; i < fr.length; i++) {
            fr[i] = 0;
        }
        fr[97] = 5; fr[98] = 9; fr[99] = 12; fr[100] = 13; fr[101] = 16; fr[102] = 45;
        HCTree t = new HCTree();
        t.buildTree(fr);

        byte a = (byte) 'a';
        byte b = (byte) 'b';
        byte c = (byte) 'c';
        byte d = (byte) 'd';
        byte e = (byte) 'e';
        byte f = (byte) 'f';

        //build example tree
        HCTree example = new HCTree();
        HCTree.HCNode AandB = example.new HCNode(a, 14);
        HCTree.HCNode A = example.new HCNode(a, 5);
        HCTree.HCNode B = example.new HCNode(b, 9);
        AandB.setC0(A); AandB.setC1(B); A.setParent(AandB);B.setParent(AandB);

        HCTree.HCNode ePar = example.new HCNode(a, 30);
        HCTree.HCNode E = example.new HCNode(e, 16);
        ePar.setC0(AandB); ePar.setC1(E); AandB.setParent(ePar);E.setParent(ePar);

        HCTree.HCNode CandD = example.new HCNode(c, 25);
        HCTree.HCNode C = example.new HCNode(c, 12);
        HCTree.HCNode D = example.new HCNode(d, 13);
        CandD.setC0(C); CandD.setC1(D); C.setParent(CandD);D.setParent(CandD);

        HCTree.HCNode n1 = example.new HCNode(a, 55);
        n1.setC0(CandD); n1.setC1(ePar); CandD.setParent(n1);ePar.setParent(n1);

        HCTree.HCNode F = example.new HCNode(f, 45);
        HCTree.HCNode root = example.new HCNode(f, 100);
        root.setC0(F); root.setC1(n1); F.setParent(root);n1.setParent(root);example.setRoot(root);
        assertTrue(sameTreeStructure(root, t.getRoot()));

        // test encode and decode
        assertTrue(testByte(t, f));
        assertTrue(testTree(t));
    }

    @Test
    public void example3() throws IOException{
        // an hctree with a 17 and b 8 and c 5
        int[] f = new int[255];
        for (int i = 0; i < f.length; i++) {
            if (i == 97) {
                f[i] = 17;
            } else if (i == 98) {
                f[i] = 8;
            } else if (i == 99) {
                f[i] = 5;
            } else {
                f[i] = 0;
            }
        }
        HCTree t = new HCTree();
        t.buildTree(f);
        
        byte byteForA = (byte) 'a';
        byte byteForB = (byte) 'b';
        byte byteForC = (byte) 'c';
        //build example tree
        HCTree example = new HCTree();
        HCTree.HCNode root = example.new HCNode(byteForA, 25);
        HCTree.HCNode A = example.new HCNode(byteForA, 17);
        HCTree.HCNode B = example.new HCNode(byteForB, 8);
        HCTree.HCNode C = example.new HCNode(byteForB, 5);
        HCTree.HCNode BC = example.new HCNode(byteForB, 17);
        root.setC0(BC); root.setC1(A); BC.setParent(root);A.setParent(root);example.setRoot(root);
        BC.setC0(C);BC.setC1(B);C.setParent(BC);B.setParent(BC);
        assertTrue(sameTreeStructure(root, t.getRoot()));

        assertTrue(testByte(t, byteForA));
        assertTrue(testTree(t));
    }

}
