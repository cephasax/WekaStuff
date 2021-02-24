package br.ufrn.imd.ebssb.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class RemoveId {

	public static File[] files;
	
	public static void main(String[] args) throws Exception {
		AbrirTodos();
		
		FileInputStream inFile;
		InputStreamReader in;

		Instances base;
		Instances base2;
		for (int i = 0; i < files.length; i++) {
			inFile = new FileInputStream(files[i]);
			in = new InputStreamReader(inFile);
			base = new Instances(in);
			
			int[] indices = {0};

			Remove removeFilter = new Remove();
			removeFilter.setAttributeIndicesArray(indices);
			removeFilter.setInvertSelection(false);
			removeFilter.setInputFormat(base);
			base2 = Filter.useFilter(base, removeFilter);
			
			ArffSaver s = new ArffSaver();
	        s.setInstances(base2);
	        s.setFile(new File(files[i].getName()));
	        s.writeBatch();
			
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
