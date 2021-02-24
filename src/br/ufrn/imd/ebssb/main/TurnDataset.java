package br.ufrn.imd.ebssb.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.AddID;

public class TurnDataset {

	public static ArrayList<Dataset> datasets;
	public static int numFolds = 10;
	public static ArrayList<Dataset> folds;
	public static int seed;

	public static File[] files;
	
	public static void main(String[] args) throws Exception {
		AbrirTodos();
		
		FileInputStream inFile;
		InputStreamReader in;
		
		FileOutputStream fos;
		Instances base;
		Instances base2;
		for (int i = 0; i < files.length; i++) {
			inFile = new FileInputStream(files[i]);
			in = new InputStreamReader(inFile);
			base = new Instances(in);
			
			AddID filter = new AddID();
			// 2. numeric attribute
		    filter.setAttributeName("id");
		    filter.setInputFormat(base);
			base2 = Filter.useFilter(base, filter);
			fos = new FileOutputStream(files[i].getName(), false);
			fos.write(base2.toString().getBytes());
			fos.close();
		}
	}


	private static void AbrirTodos() {

		String url = "C:";

		JFileChooser chooser = null;
		chooser = new JFileChooser(url);
		chooser.setMultiSelectionEnabled(true);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			files = chooser.getSelectedFiles();
		}
	}
	
}
