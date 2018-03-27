package de.web.program.encryptor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
	
	public static ArrayList<Character> abc = new ArrayList<Character>();
	public static String[] modes = {"Encrypt", "Decrypt"};
	public static String[] formats = {".txt", ".dat", ".jfe", ".yml", ".xml", ".w44", ".flc", ".fef", ".crypt"};
	public static final String path = "Files/";
	public static final String langpath = "Lang/";
	public static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	
	public static String button_process = "button.process";
	public static String checkbox_delete_when_done = "checkbox.delete_when_done";
	public static String label_state = "label.state";
	
	public static void main(String[] args) throws Exception {
		
		abc.add('a');
		abc.add('b');
		abc.add('c');
		abc.add('d');
		abc.add('e');
		abc.add('f');
		abc.add('g');
		abc.add('h');
		abc.add('i');
		abc.add('j');
		abc.add('k');
		abc.add('l');
		abc.add('m');
		abc.add('n');
		abc.add('o');
		abc.add('p');
		abc.add('q');
		abc.add('r');
		abc.add('s');
		abc.add('t');
		abc.add('u');
		abc.add('v');
		abc.add('w');
		abc.add('x');
		abc.add('y');
		abc.add('z');
		abc.add('A');
		abc.add('B');
		abc.add('C');
		abc.add('D');
		abc.add('E');
		abc.add('F');
		abc.add('G');
		abc.add('H');
		abc.add('I');
		abc.add('J');
		abc.add('K');
		abc.add('L');
		abc.add('M');
		abc.add('N');
		abc.add('O');
		abc.add('P');
		abc.add('Q');
		abc.add('R');
		abc.add('S');
		abc.add('T');
		abc.add('U');
		abc.add('V');
		abc.add('W');
		abc.add('X');
		abc.add('Y');
		abc.add('Z');
		abc.add('!');
		abc.add('?');
		
		
		File filedir = new File(path);
		if (!filedir.exists()) {
			filedir.mkdirs();
		}
		
		File langdir = new File(langpath);
		if (!langdir.exists()) {
			langdir.mkdirs();
		}
		
		File configfile = new File(path + "../", "config.yml");
		if (!configfile.exists()) {
			configfile.createNewFile();
			Cryptor.writeToFile(configfile, "language: de");
		}
		
		String usedlang = "raw";
		
		for (String line : Cryptor.readLinesFromFile(configfile)) {
			if (line.contains("language: ")) {
				line = line.replace("language: ", "");
				usedlang = line;
			}
		}
		
		File langfile = new File(langpath, usedlang + ".lang");
		if (!langfile.exists()) {
			JFrame error = new JFrame("File Encryptor - Error");
			error.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			error.setResizable(false);
			error.setPreferredSize(new Dimension(300, 100));
			error.setUndecorated(false);
			error.setLocation(800, 400);
			JLabel el = new JLabel();
			el.setText(usedlang + ".lang not found!");
			el.setForeground(Color.BLUE);
			JPanel ep = new JPanel();
			ep.add(el);
			error.add(ep);
			error.pack();
			error.setVisible(true);
			Thread.sleep(3000);
			Runtime.getRuntime().exit(0);
		}
		for (String line : Cryptor.readLinesFromFile(langfile)) {
			if (line.contains("button.process=")) {
				line = line.replace("button.process=", "");
				button_process = new String(line.getBytes(), StandardCharsets.UTF_8);
			} else if (line.contains("checkbox.delete_when_done=")) {
				line = line.replace("checkbox.delete_when_done=", "");
				checkbox_delete_when_done = new String(line.getBytes(), StandardCharsets.UTF_8);
			} else if (line.contains("label.state=")) {
				line = line.replace("label.state=", "");
				label_state = new String(line.getBytes(), StandardCharsets.UTF_8);
			}
		}
		
		
		JFrame main = new JFrame("File Encryptor");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setResizable(false);
		main.setPreferredSize(new Dimension(300, 300));
		main.setUndecorated(false);
		main.setLocation(800, 400);
		JPanel panel = new JPanel();
		JLabel state = new JLabel();
		JCheckBox delete = new JCheckBox();
		delete.setText(checkbox_delete_when_done + "          ");
		state.setText(label_state + ": Starte");
		JTextField filename = new JTextField();
		JComboBox<String> mode = new JComboBox<String>();
		JComboBox<String> format = new JComboBox<String>();
		JButton process = new JButton();
		process.setText(button_process);
		process.setPreferredSize(new Dimension(200, 40));
		process.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (state.getText().equals(label_state + ": Bereit")) {
					File file = new File(path, filename.getText());
					if (!file.exists()) {
						state.setText("Datei nicht gefunden");
						scheduler.schedule(new Runnable() {
							
							@Override
							public void run() {
								state.setText(label_state + ": Bereit");
							}
						}, 2, TimeUnit.SECONDS);
						return;
					}
					if (mode.getSelectedIndex() == 0) {
						state.setText(label_state + ": Encrypte");
						File infoFile = null;
						File outFile = null;
						
						switch(format.getSelectedIndex()) {
						case 1:
							infoFile = new File(path, filename.getText() + "_decrypt.dat");
							outFile = new File(path, filename.getText() + "o.dat");
							break;
						case 2:
							infoFile = new File(path, filename.getText() + "_decrypt.jfe");
							outFile = new File(path, filename.getText() + "o.jfe");
							break;
						case 3:
							infoFile = new File(path, filename.getText() + "_decrypt.yml");
							outFile = new File(path, filename.getText() + "o.yml");
							break;
						case 4:
							infoFile = new File(path, filename.getText() + "_decrypt.xml");
							outFile = new File(path, filename.getText() + "o.xml");
							break;
						case 5:
							infoFile = new File(path, filename.getText() + "_decrypt.w44");
							outFile = new File(path, filename.getText() + "o.w44");
							break;
						case 6:
							infoFile = new File(path, filename.getText() + "_decrypt.flc");
							outFile = new File(path, filename.getText() + "o.flc");
							break;
						case 7:
							infoFile = new File(path, filename.getText() + "_decrypt.fef");
							outFile = new File(path, filename.getText() + "o.fef");
							break;
						case 8:
							infoFile = new File(path, filename.getText() + "_decrypt.crypt");
							outFile = new File(path, filename.getText() + "o.crypt");
							break;
						default:
							infoFile = new File(path, filename.getText() + "_decrypt.txt");
							outFile = new File(path, filename.getText() + "o.txt");
							break;
						}
						
						try {
							infoFile.createNewFile();
							outFile.createNewFile();
							String key = getRandomKey();
							byte[] keyencode = Base64.getEncoder().encode(Base64.getEncoder().encode(Base64.getEncoder().encode(Base64.getEncoder().encode(Base64.getEncoder().encode(Base64.getEncoder().encode(Base64.getEncoder().encode(Base64.getEncoder().encode(JFES.encrypt(key).getBytes()))))))));
							byte[] filenameencode = Base64.getEncoder().encode(Base64.getEncoder().encode(filename.getText().getBytes()));
							Cryptor.encrypt(key, file, outFile);
							Cryptor.writeToFile(infoFile, new String(keyencode) + System.lineSeparator() + new String(filenameencode));
							state.setText(label_state + ": Bereit");
						} catch (Exception ex) {
							state.setText(label_state + ": Error");
							scheduler.schedule(new Runnable() {
								
								@Override
								public void run() {
									state.setText(label_state + ": Bereit");
								}
							}, 2, TimeUnit.SECONDS);
							return;
						}
					} else if (mode.getSelectedIndex() == 1) {
						state.setText(label_state + ": Decrypte");
						try {
							File infoFile = new File(path, filename.getText());
							String fnd = new String(Base64.getDecoder().decode(Base64.getDecoder().decode(Cryptor.readLinesFromFile(filename.getText())[1].getBytes())));
							File inFile = null;
							File outFile = new File(path, fnd);
							switch(format.getSelectedIndex()) {
							case 1:
								inFile = new File(path, fnd + "o.dat");
								break;
							case 2:
								inFile = new File(path, fnd + "o.jfe");
								break;
							case 3:
								inFile = new File(path, fnd + "o.yml");
								break;
							case 4:
								inFile = new File(path, fnd + "o.xml");
								break;
							case 5:
								inFile = new File(path, fnd + "o.w44");
								break;
							case 6:
								inFile = new File(path, fnd + "o.flc");
								break;
							case 7:
								inFile = new File(path, fnd + "o.fef");
								break;
							case 8:
								inFile = new File(path, fnd + "o.crypt");
								break;
							default:
								inFile = new File(path, fnd + "o.txt");
								break;
							}
							
							String key = JFES.decrypt(new String(Base64.getDecoder().decode(Base64.getDecoder().decode(Base64.getDecoder().decode(Base64.getDecoder().decode(Base64.getDecoder().decode(Base64.getDecoder().decode(Base64.getDecoder().decode(Base64.getDecoder().decode(Cryptor.readLinesFromFile(infoFile)[0]))))))))));
							outFile.createNewFile();
							Cryptor.decrypt(key, inFile, outFile);
							if (delete.isSelected()) {
								inFile.delete();
								infoFile.delete();
							}
							state.setText(label_state + ": Bereit");
						} catch (Exception e1) {
							e1.printStackTrace();
							state.setText(label_state + ": Error");
							scheduler.schedule(new Runnable() {
								
								@Override
								public void run() {
									state.setText(label_state + ": Bereit");
								}
							}, 2, TimeUnit.SECONDS);
							return;
						}
					}
				}
			}
		});
		mode.setEditable(false);
		mode.addItem(modes[0]);
		mode.addItem(modes[1]);
		for (String cf : formats) {
			format.addItem(cf);
		}
		filename.setPreferredSize(new Dimension(200, 30));
		mode.setPreferredSize(new Dimension(200, 30));
		panel.add(mode);
		panel.add(format);
		panel.add(filename);
		panel.add(process);
		panel.add(delete);
		panel.add(state);
		main.add(panel);
		main.pack();
		main.setVisible(true);
		state.setText(label_state + ": Bereit");
	}
	
	public static String getRandomKey() {
		
		Random r = new Random();
		String key = "";
		for (int i = 0; i < 16; i++) {
			key = key + abc.get(r.nextInt(53));
		}
		
		return key;
	}
	
}
