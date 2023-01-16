/*
 * Name: Zehui Zhang
 * PID:  A16151490
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Zehui Zhang
 * @since  2021-02-14
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                // populate three trees with the information you just read
                // hint: create a helper function and reuse it to build all three trees

                populateHelper(movieTree, cast, movie);
                populateHelper(studioTree, studios, movie);
                populateHelperCaseSen(ratingTree, cast, rating);
                
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Populate a BST
     * 
     * @param tree - BST to be populated
     * @param keys - keys to be populated
     * @param myvalue - value of keys
     */
    public static BSTree<String> populateHelper(BSTree<String> tree, String[] keys, String myvalue) {
        String value = myvalue.toLowerCase();

        for(String key : keys) {
            if(tree.insert(key.toLowerCase())) {
                tree.insertData(key.toLowerCase(), value);               
            } else {
                if (!tree.findDataList(key.toLowerCase()).contains(value)){
                    tree.insertData(key.toLowerCase(), value);
                }
            }
        }
        return tree;
    }

     /**
     * Populate a BST
     * 
     * @param tree - BST to be populated
     * @param keys - keys to be populated
     * @param myvalue - value of keys
     */
    public static BSTree<String> populateHelperCaseSen(BSTree<String> tree, String[] keys, String value) {
        for(String key : keys) {
            if(tree.insert(key)) {
                tree.insertData(key, value);               
            } else {
                if (!tree.findDataList(key).contains(value)){
                    tree.insertData(key, value);
                }
            }
        }
        return tree;
    }
    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        // process query
        String[] keys = query.toLowerCase().split(" ");
        LinkedList<String> history = new LinkedList<>();
        LinkedList<String> retained = new LinkedList<>();
        retained.addAll(searchTree.findDataList(keys[0]));
        String comb = "";
        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful
        if (keys.length > 1) {
            for (int i = 0; i < keys.length; i++) {
                retained.retainAll(searchTree.findDataList(keys[i]));
                if (i == keys.length - 1) {
                    comb += keys[i];
                } else {
                    comb += keys[i];
                    comb += " ";
                }
            }
            print(comb, retained);
            history.addAll(retained);
        }

        // search and output results
        // hint: list's addAll() and retainAll() methods could be helpful
        for (String key : keys) {
            // find the key in search tree
            boolean contained = false;
            if (searchTree.findKey(key)) {
                LinkedList<String> dataList = searchTree.findDataList(key);
                // documents are all printed before
                if (history.containsAll(dataList)) {
                    dataList.clear();
                    contained = true;
                } else { //some documents are not printed before
                    dataList.removeAll(history);
                    history.addAll(dataList);
                }
                if(!contained) {
                    print(key, dataList);
                }
                

            } else { // do not find the key in search tree
                LinkedList<String> myLL = new LinkedList<String>();
                print(key, myLL);
            }
        }
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        
        // initialize search trees
        BSTree<String> movieTree = new BSTree<>();
        BSTree<String> studioTree = new BSTree<>();
        BSTree<String> ratingTree = new BSTree<>();

        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        String query = "";
        for (int i = 2; i < args.length; i++) {
            query += args[i];
            query += " ";
        }

        // populate search trees
        populateSearchTrees(movieTree, studioTree, ratingTree, fileName);

        // choose the right tree to query
        if (searchKind == 0) {
            searchMyQuery(movieTree, query);
        } else if (searchKind == 1) {
            searchMyQuery(studioTree, query);
        } else if (searchKind == 2) {
            searchMyQuery(ratingTree, query);
        }
    }
}
