package test;

import stockage.*;

import java.util.Vector;

import Pipeline.FileIterator;
import Pipeline.FullScanIterator;
import Pipeline.JointureIterator;
import Pipeline.ProjectionIterator;
import Pipeline.RestrictionEgaliteIterator;
import Pipeline.RestrictionInferieurIterator;
import Pipeline.RestrictionSuperieurIterator;
import impl.*;

public class Main {
	
	public static final int datasetSize = 6;
	public static final int nupletSize = 3;

	public static void main(String[] args){
		
		//Création Table 1
		// 6 lignes et 3 colonnes
		Nuplet[] table1 = new NupletInt[datasetSize];
		byte[] l0 = {0,53,16};
		byte[] l1 = {1,67,11};
		byte[] l2 = {2,43,21};
		byte[] l3 = {3,53,22};
		byte[] l4 = {4,53,16};
		byte[] l5 = {5,34,30};
		table1[0] = new NupletInt(l0);
		table1[1] = new NupletInt(l1);
		table1[2] = new NupletInt(l2);
		table1[3] = new NupletInt(l3);
		table1[4] = new NupletInt(l4);
		table1[5] = new NupletInt(l5);
		
		
		//Création Table 2
		// 6 lignes et 3 colonnes
		Nuplet[] table2 = new NupletInt[datasetSize];
		byte[] l6 = {0,49,16};
		byte[] l7 = {1,35,11};
		byte[] l8 = {2,53,11};
		byte[] l9 = {3,53,39};
		byte[] l10 = {4,48,16};
		byte[] l11 = {5,68,12};
		table2[0] = new NupletInt(l6);
		table2[1] = new NupletInt(l7);
		table2[2] = new NupletInt(l8);
		table2[3] = new NupletInt(l9);
		table2[4] = new NupletInt(l10);
		table2[5] = new NupletInt(l11);
		
		// Enregistrement et affichage des tables (dans le dossier courant)
		
		System.out.println("------------------------------------------------");	
		System.out.println("Enregistrement de la table 1");
		Table t1 = new TableInt("table1", nupletSize);
		for(int i=0;i<datasetSize;i++){
			t1.put(table1[i]);
			}
		System.out.println("------------------------------------------------");	
		System.out.println("Lecture de la table 1");
		System.out.println("------------------------------------------------");	
		for(int i=0;i<datasetSize;i++){
			System.out.println(t1.get(i).toString());
			}
		
		System.out.println("------------------------------------------------");	
		System.out.println("Enregistrement de la table 2");
		Table t2 = new TableInt("table2", nupletSize);
		for(int i=0;i<datasetSize;i++){
			t2.put(table2[i]);
			}
		System.out.println("------------------------------------------------");	
		System.out.println("Lecture de la table 2");
		System.out.println("------------------------------------------------");	
		for(int i=0;i<datasetSize;i++){
			System.out.println(t2.get(i).toString());
			}
		
		// Utilisation de getByAtt
		System.out.println("------------------------------------------------");	
		System.out.println("Test fonction GetByAtt, Table 1, att = 1, v = 53");
		System.out.println("------------------------------------------------");	
		Nuplet[] res = t1.getByAtt(1, (byte)53);
		for(Nuplet n : res) {
			System.out.println(n.toString());
		}
		
		// Utilisation de fullscan		
		System.out.println("------------------------------------------------");	
		System.out.println("Test fonction FullScan, Table 1");
		System.out.println("------------------------------------------------");	
		
		//Appel de la fonction FullScan
		Nuplet[] res_fs_table1 = t1.fullScan();
		
		//Affichage de la sortie de la fonction
		for(Nuplet n : res_fs_table1) {
			System.out.println(n.toString());
		}
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test fonction FullScan, Table 2");
		System.out.println("------------------------------------------------");
		
		//Appel de la fonction FullScan
		Nuplet[] res_fs_table2 = t2.fullScan();
		
		//Affichage de la sortie de la fonction
		for(Nuplet n : res_fs_table2) {
			System.out.println(n.toString());
		}
		
		// Utilisation des resctrictions
		
		// Création d'une instance de Restriction
		RestrictionInt r = new RestrictionInt();
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test restriction, Table 1, att 1 == 53");
		System.out.println("------------------------------------------------");	
		
		//Appel de la fonction egalite
		Nuplet[] res3 = r.egalite(res_fs_table1, 1, (byte)53);
		
		//Affichage de la sortie de la fonction
		for(Nuplet n : res3) {
			System.out.println(n.toString());
		}
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test restriction, Table 1, att 1 > 53");
		System.out.println("------------------------------------------------");
		
		//Appel de la fonction superieur
		Nuplet[] res4 = r.superieur(res_fs_table1, 1, (byte)53);
		for(Nuplet n : res4) {
			System.out.println(n.toString());
		}
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test restriction, Table 1, att 1 < 53");
		System.out.println("------------------------------------------------");
		
		//Appel de la fonction inferieur
		Nuplet[] res5 = r.inferieur(res_fs_table1, 1, (byte)53);
		
		//Affichage de la sortie de la fonction
		for(Nuplet n : res5) {
			System.out.println(n.toString());
		}
		
		
		// Utilisation de la projection
		
		// Création d'une instance de Projection
		ProjectionImpl p = new ProjectionImpl();
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test projection, Table 1, atts = {0,2}");
		System.out.println("------------------------------------------------");
		
		// Tableau d'attributs
		int[] atts = {0,2}; 
		
		//Appel de la fonction project
		Nuplet[] res6 = p.project(res_fs_table1, atts);
		
		//Affichage de la sortie de la fonction
		for(Nuplet n : res6) {
			System.out.println(n.toString());
		}
		
		// Création d'une instance de JointureH
		JointureH jhash = new JointureH();
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test jointure hash, Table 1, Table 2, att1 = 1, att2 = 1");
		System.out.println("------------------------------------------------");	
		
		//Appel de la fonction jointure
		Nuplet[] res7 = jhash.jointure(res_fs_table1,res_fs_table2,1,1);
		//Affichage de la sortie de la fonction
		for(Nuplet n : res7) {
			System.out.println(n.toString());
		}
		
		// Création d'une instance de JointureBI
		JointureBI jbi = new JointureBI();
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test jointure BI, Table 1, Table 2, att1 = 1, att2 = 1");
		System.out.println("------------------------------------------------");
		
		//Appel de la fonction jointure
		Nuplet[] res8 = jbi.jointure(res_fs_table1,res_fs_table2,1,1);
		
		//Affichage de la sortie de la fonction
		for(Nuplet n : res8) {
			System.out.println(n.toString());
		}
		
		// Création d'une instance de JointureS
		JointureS jsm = new JointureS();
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test jointure S, Table 1, Table 2, att1 = 1, att2 = 1");
		System.out.println("------------------------------------------------");	
		
		//Appel de la fonction jointure
		Nuplet[] res9 = jsm.jointure(res_fs_table1,res_fs_table2,1,1);
		
		//Affichage de la sortie de la fonction
		for(Nuplet n : res9) {
			System.out.println(n.toString());
		}
		
		System.out.println("\n\n------------------------------------------------");	
		System.out.println(" PIPELINE MODE ON");
		System.out.println("------------------------------------------------\n");	
		
		//La classe FileIterator retourne à chaque next ligne suivante du fichier donné en paramètre sous forme de Nuplet
		
		//Création des 2 instances de FileIterator pour chacune des tables
		FileIterator fi = new FileIterator("table1", nupletSize);
		FileIterator fi2 = new FileIterator("table2", nupletSize);
		Nuplet n = null;
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test fonction FullScan PIPELINE, Table 1");
		System.out.println("------------------------------------------------");
		
		//Création d'une instance de FullScanIteraotr avec le FileIterator de la table 1
		FullScanIterator fsi = new FullScanIterator(fi);
		
		Vector<Nuplet> vectIterator1 = new Vector<Nuplet>();
		
		//Ouverture du FilIterator + position mise à 0
		fsi.open();
		
		//Récupération de la ligne suivante de la table + incrémentation de la position
		n = fsi.next();
		while(n != null) {
			System.out.println(n);
			vectIterator1.addElement(n);
			//Récupération de la ligne suivante de la table + incrémentation de la position
			n = fsi.next();
		}
		
		//Récupération des sorties de la fonction next et affichage du résultat
		Nuplet[] retIterator1 = new Nuplet[vectIterator1.size()];
		for(int i=0;i<vectIterator1.size();i++)
			retIterator1[i] = vectIterator1.elementAt(i);
		
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test restriction PIPELINE, Table 1, att 1 == 53");
		System.out.println("------------------------------------------------");
		
		//Création d'une instance de RestrictionEgaliteIterator avec le FileIterator de la table 1
		RestrictionEgaliteIterator rei = new RestrictionEgaliteIterator(fi, 1, (byte) 53);
		Vector<Nuplet> vectIterator2 = new Vector<Nuplet>();
		
		//Ouverture du FilIterator + position mise à 0
		rei.open();
		
		//Récupération de la ligne suivante de la table + incrémentation de la position
		n = rei.next();
		while(n != null) {
			System.out.println(n);
			vectIterator2.addElement(n);
			//Récupération de la ligne suivante de la table + incrémentation de la position
			n = rei.next();
		}
		
		//Récupération des sorties de la fonction next et affichage du résultat
		Nuplet[] retIterator2 = new Nuplet[vectIterator2.size()];
		for(int i=0;i<vectIterator2.size();i++)
			retIterator2[i] = vectIterator2.elementAt(i);
		
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test restriction PIPELINE, Table 1, att 1 > 53");
		System.out.println("------------------------------------------------");
		
		//Création d'une instance de RestrictionSuperieurIterator avec le FileIterator de la table 1
		RestrictionSuperieurIterator rsi = new RestrictionSuperieurIterator(fi, 1, (byte) 53);
		Vector<Nuplet> vectIterator3 = new Vector<Nuplet>();
		
		//Ouverture du FilIterator + position mise à 0
		rsi.open();
		
		//Récupération de la ligne suivante de la table + incrémentation de la position
		n = rsi.next();
		while(n != null) {
			System.out.println(n);
			vectIterator3.addElement(n);
			//Récupération de la ligne suivante de la table + incrémentation de la position
			n = rsi.next();
		}
		
		//Récupération des sorties de la fonction next et affichage du résultat
		Nuplet[] retIterator3 = new Nuplet[vectIterator3.size()];
		for(int i=0;i<vectIterator3.size();i++)
			retIterator3[i] = vectIterator3.elementAt(i);
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test restriction PIPELINE, Table 1, att 1 < 53");
		System.out.println("------------------------------------------------");	
		
		//Création d'une instance de RestrictionInferieurIterator avec le FileIterator de la table 1
		RestrictionInferieurIterator rii = new RestrictionInferieurIterator(fi, 1, (byte) 53);
		Vector<Nuplet> vectIterator4 = new Vector<Nuplet>();
		
		//Ouverture du FilIterator + position mise à 0
		rii.open();
		
		//Récupération de la ligne suivante de la table + incrémentation de la position
		n = rii.next();
		while(n != null) {
			System.out.println(n);
			vectIterator4.addElement(n);
			//Récupération de la ligne suivante de la table + incrémentation de la position
			n = rii.next();
		}
		
		//Récupération des sorties de la fonction next et affichage du résultat
		Nuplet[] retIterator4 = new Nuplet[vectIterator4.size()];
		for(int i=0;i<vectIterator4.size();i++)
			retIterator4[i] = vectIterator4.elementAt(i);
		
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test projection PIPELINE, Table 1, atts = {0,2}");
		System.out.println("------------------------------------------------");	
		//int[] atts = {1,3,5}; 
		
		//Création d'une instance de ProjectionIterator avec le FileIterator de la table 1
		ProjectionIterator pi = new ProjectionIterator(fi, atts);
		Vector<Nuplet> vectIterator5 = new Vector<Nuplet>();
		
		//Ouverture du FilIterator + position mise à 0
		pi.open();
		
		//Récupération de la ligne suivante de la table + incrémentation de la position
		n = pi.next();
		while(n != null) {
			System.out.println(n);
			vectIterator5.addElement(n);
			//Récupération de la ligne suivante de la table + incrémentation de la position
			n = pi.next();
		}
		
		//Récupération des sorties de la fonction next et affichage du résultat
		Nuplet[] retIterator5 = new Nuplet[vectIterator5.size()];
		for(int i=0;i<vectIterator5.size();i++)
			retIterator5[i] = vectIterator5.elementAt(i);
		
		System.out.println("------------------------------------------------");	
		System.out.println("Test jointure PIPELINE, Table 1, Table 2, att1 = 1, att2 = 1");
		System.out.println("------------------------------------------------");	
		
		//Création d'une instance de JointureIterator avec le FileIterator de la table 1
		JointureIterator ji = new JointureIterator(fi, fi2,1,1);
		Vector<Nuplet> vectIterator6 = new Vector<Nuplet>();
		
		//Ouverture du FilIterator + position mise à 0
		ji.open();
		
		//Récupération de la ligne suivante de la table + incrémentation de la position
		n = ji.next();
		while(n != null) {
			System.out.println(n);
			vectIterator6.addElement(n);
			//Récupération de la ligne suivante de la table + incrémentation de la position
			n = ji.next();
		}
		
		//Récupération des sorties de la fonction next et affichage du résultat
		Nuplet[] retIterator6 = new Nuplet[vectIterator6.size()];
		for(int i=0;i<vectIterator6.size();i++)
			retIterator6[i] = vectIterator6.elementAt(i);
	}
}
