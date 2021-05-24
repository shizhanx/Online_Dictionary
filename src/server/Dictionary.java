package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Shizhan Xu, 771900
 * University of Melbourne
 * All rights reserved
 */
public class Dictionary {
    /**
     * A data structure to store the dictionary in runtime memory
     * You are safe to treat it as an ordinary list.
     */
    private class Trie {
        private final Map<Character, Trie> children;
        private String meanings;

        public Trie() {
            children = new HashMap<>();
            meanings = null;
        }

        /**
         * Add a word to this trie.
         * @param arr the array representation of the input word
         * @param i the current index we have traversed in the array
         * @param meanings the meanings of this word
         * @return whether the word exists already and we cannot add,
         * or we added successfully.
         */
        protected synchronized boolean add(char[] arr, int i, String meanings) {
            if (i == arr.length) {
                if (this.meanings != null) return false;
                this.meanings = meanings;
                return true;
            }
            if (!children.containsKey(arr[i])) children.put(arr[i], new Trie());
            return children.get(arr[i]).add(arr, i + 1, meanings);
        }

        /**
         * Remove a word from the trie.
         * @param arr the array representation of this word
         * @param i the index we have traversed in the array
         * @return whether the word exists and we successfully deleted it
         * or there is no such word
         */
        protected synchronized boolean remove(char[] arr, int i) {
            if (i == arr.length) {
                if (meanings == null) return false;
                meanings = null;
                return true;
            }
            if (!children.containsKey(arr[i])) return false;
            return children.get(arr[i]).remove(arr, i + 1);
        }

        /**
         * Search a word in the trie
         * @param arr the array representation of this word
         * @param i the index we have traversed in the array
         * @return the meanings of this word, or null if it doesn't exist
         */
        protected synchronized String search(char[] arr, int i) {
            if (i == arr.length) return meanings;
            if (!children.containsKey(arr[i])) return null;
            return children.get(arr[i]).search(arr, i + 1);
        }

        protected synchronized boolean update(char[] arr, int i, String meanings) {
            if (i == arr.length) {
                if (this.meanings == null) return false;
                this.meanings = meanings;
                return true;
            }
            if (!children.containsKey(arr[i])) return false;
            return children.get(arr[i]).update(arr, i + 1, meanings);
        }
    }

    private final Trie trie;

    /**
     * Construct the initial dictionary
     * @param filePath the file of initial dictionary inputs
     */
    public Dictionary(String filePath) {
        trie = new Trie();
        // Read the initial dictionary file from resources
        try (BufferedReader bufferedReader =
                     new BufferedReader(new InputStreamReader(
                             getClass().getResourceAsStream(filePath)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // The first element is the word itself, while all the following
                // ones are the meanings. We encode with --- at the beginning
                String[] entry = line.split(",");
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < entry.length; i++) {
                    if (!entry[i].isEmpty()) {
                        sb.append("--");
                        sb.append(entry[i]);
                        sb.append('\n');
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                trie.add(entry[0].toCharArray(), 0, sb.toString());
            }
        } catch (NullPointerException e) {
            ServerError.argError("wrong file name");
        } catch (IOException e) {
            ServerError.argError("Something went wrong when reading the file");
        }
    }

    /**
     * Search a word in the dictionary
     * @param word the word to search
     * @return the meanings of this word, or null if not exist
     */
    public synchronized String search(String word) {
        System.out.println("Now searching: " + word);
        return trie.search(word.toCharArray(), 0);
    }

    /**
     * Add a new word to this dictionary. Check if it already exists
     * @param word the word to add
     * @param meanings the meaning of the word. Should be non-empty
     * @return whether successfully added or the word already exists
     */
    public synchronized boolean add(String word, String meanings) {
        System.out.println("Now adding: " + word);
        return trie.add(word.toCharArray(), 0, meanings);
    }

    /**
     * Delete a word from the dictionary. Check if it exists
     * @param word the ward to delete
     * @return whether successfully deleted or the word doesn't exist
     */
    public synchronized boolean delete(String word) {
        System.out.println("Now deleting: " + word);
        return trie.remove(word.toCharArray(), 0);
    }

    /**
     * Edit an existing word's meanings. This method is a combination of
     * delete and add actions.
     * @param word the word to edit
     * @param meanings the new meanings
     * @return whether successfully updated or the word doesn't exist
     */
    public synchronized boolean update(String word, String meanings) {
        System.out.println("Now updating: " + word);
        return trie.update(word.toCharArray(), 0, meanings);
    }
}
