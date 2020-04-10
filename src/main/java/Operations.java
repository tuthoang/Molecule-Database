package main.java;

import java.io.File;
import java.sql.SQLException;
import java.util.Random;

public class Operations {
    private static H2DB db;
    //    searchDumb search;

    Operations(){
//        search = new searchDumb();
        db = new H2DB();
        db.connect();
    }


    public void insert(String filename){
        try {
            db.insertMolecule(filename);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean checkIsomorphism(MoleculeAbstract m1, MoleculeAbstract m2){

        boolean weakIsomorphism = searchDumb.isIsomorphicWithNumbers(m1, m2);
        if(weakIsomorphism) return searchDumb.verify_rigorous_isomorphism(m1, m2);
        return false;
    }

    public static void main(String[] args) {
        Operations Ops = new Operations();
        Random rand = new Random();

        File moleculesDir = new File("molecules");
        File[] listOfFiles = moleculesDir.listFiles();
        System.out.println(listOfFiles[6].getAbsolutePath());
//        try{
//            String s = listOfFiles[6].getAbsolutePath();
//            Ops.insert(s);
//            MoleculeText m = new MoleculeText(s);
//             db.queryAdjacencyList(m.moleculeName);
//            System.out.println(m.numVertices);
//            System.out.println(m.toString());

//            MoleculeDB[] molecules = db.findSameNumberAtoms(m.numVertices, m.getAtomList());
////            System.out.println(molecules.length);
//            for (MoleculeDB molecule : molecules) {
//                m.resetAtoms();
//                if (m.getMoleculeName().equals(molecule.getMoleculeName())){
//                    System.out.println(m.getMoleculeName());
//                    System.out.println(m.toString());
//                    System.out.println(molecule.toString());
//                }
//                if(Ops.checkIsomorphism(m, molecule))
//                    System.out.println(m.getMoleculeName() + "is isomorphic with "+ molecule.moleculeName);
//        }

        for(int i = 0; i < 10; i++) {
            try {
                long startTime = System.nanoTime();

                // Five inserts
                Ops.insert(listOfFiles[rand.nextInt(listOfFiles.length)].getAbsolutePath());
                Ops.insert(listOfFiles[rand.nextInt(listOfFiles.length)].getAbsolutePath());
                Ops.insert(listOfFiles[rand.nextInt(listOfFiles.length)].getAbsolutePath());
                Ops.insert(listOfFiles[rand.nextInt(listOfFiles.length)].getAbsolutePath());
                Ops.insert(listOfFiles[rand.nextInt(listOfFiles.length)].getAbsolutePath());


                // 1 iso
                MoleculeText m = new MoleculeText(listOfFiles[rand.nextInt(listOfFiles.length)].getAbsolutePath());
                MoleculeDB[] molecules = db.findSameNumberAtoms(m.numVertices, m.getAtomList());

                for (MoleculeDB molecule : molecules) {

                    if(Ops.checkIsomorphism(m, molecule))
                        System.out.println(m.getMoleculeName() + "is isomorphic with "+ molecule.moleculeName);
                }

                // 2 iso
                m = new MoleculeText(listOfFiles[rand.nextInt(listOfFiles.length)].getAbsolutePath());
                molecules = db.findSameNumberAtoms(m.numVertices, m.getAtomList());

                for (MoleculeDB molecule : molecules) {
                    if(Ops.checkIsomorphism(m, molecule))
                        System.out.println(m.getMoleculeName() + "is isomorphic with "+ molecule.moleculeName);
                }

                // 3 iso
                m = new MoleculeText(listOfFiles[rand.nextInt(listOfFiles.length)].getAbsolutePath());
                molecules = db.findSameNumberAtoms(m.numVertices, m.getAtomList());

                for (MoleculeDB molecule : molecules) {
                    if(Ops.checkIsomorphism(m, molecule))
                        System.out.println(m.getMoleculeName() + "is isomorphic with "+ molecule.moleculeName);
                }

                // 4 iso
                m = new MoleculeText(listOfFiles[rand.nextInt(listOfFiles.length)].getAbsolutePath());
                molecules = db.findSameNumberAtoms(m.numVertices, m.getAtomList());

                for (MoleculeDB molecule : molecules) {
                    if(Ops.checkIsomorphism(m, molecule))
                        System.out.println(m.getMoleculeName() + "is isomorphic with "+ molecule.moleculeName);
                }

                // 5 iso
                m = new MoleculeText(listOfFiles[rand.nextInt(listOfFiles.length)].getAbsolutePath());
                molecules = db.findSameNumberAtoms(m.numVertices, m.getAtomList());

                for (MoleculeDB molecule : molecules) {
                    if(Ops.checkIsomorphism(m, molecule))
                        System.out.println(m.getMoleculeName() + "is isomorphic with "+ molecule.moleculeName);
                }

                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Operation Time: " + duration / 1000000 + "ms");
                System.out.println("--------------------------------------------");

            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
