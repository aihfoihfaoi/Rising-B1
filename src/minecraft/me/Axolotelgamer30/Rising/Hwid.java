package me.Axolotelgamer30.Rising;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

public class Hwid {
	
	public static void main() {

		String combinedstring = getMotherboardSN() + ":" + getDriveSN("C") + ":" + getGpuSN();

		String sha256hash = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(combinedstring.getBytes());

			StringBuilder hexstring = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1)
					hexstring.append('0');
				hexstring.append(hex);
			}
			sha256hash = hexstring.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// System.out.println("combined string: " + combinedstring);
		// System.out.println("SHA-256 Hash: " + sha256hash);

		if (!checkhwid(sha256hash)) {
			int choice = JOptionPane.showConfirmDialog(null,
					"Your Hardware ID (HWID) appears to be invalid. If you believe this is a mistake, please contact real_bacon45 on Discord.\n"
							+ "Would you like to copy your HWID to your clipboard?",
					"Invalid HWID", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if (choice == JOptionPane.YES_OPTION) {
				StringSelection selection = new StringSelection(sha256hash);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(selection, selection);
			}

			System.exit(0);
		}
	}

	public static String getDriveSN(String drive) {
		String result = "";
		try {
			File file = File.createTempFile("drive", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);

			String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
					+ "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
					+ "Wscript.Echo objDrive.SerialNumber";
			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.trim().replace("-", "");
	}

	public static String getMotherboardSN() {
		String result = "";
		try {
			File file = File.createTempFile("mobo", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);

			String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
					+ "Set colItems = objWMIService.ExecQuery _ \n" + "   (\"Select * from Win32_BaseBoard\") \n"
					+ "For Each objItem in colItems \n" + "    Wscript.Echo objItem.SerialNumber \n"
					+ "    exit for  ' do the first cpu only! \n" + "Next \n";

			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.trim();
	}

	public static String getGpuSN() {
		StringBuilder gpuserialnumber = new StringBuilder();
		String lastline = null;
		try {
			Process process = Runtime.getRuntime().exec("wmic path win32_videocontroller get caption,PNPDeviceID");

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("PCI\\")) {
					lastline = line;
				}
			}

			if (lastline != null) {
				int startIndex = lastline.indexOf("PCI\\");
				gpuserialnumber.append(lastline.substring(startIndex));
			}

			reader.close();

			int exitCode = process.waitFor();
			if (exitCode != 0) {
				System.err.println("error executing command exit code: " + exitCode);
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return gpuserialnumber.toString().replaceFirst("PCI", "").replace("\\", "");
	}

	public static boolean checkhwid(String hwid) {
		String urlstr = "https://rising-api.onrender.com/CheckHwid?sha256=" + hwid;
		try {
			URL url = new URL(urlstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.flush();
			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String response = reader.readLine();
			reader.close();

			conn.disconnect();
			
			//System.out.println(url);
			//System.out.println(response);
			boolean result = Boolean.parseBoolean(response);
			if (result) {
				// System.out.println("string found");
				return true;
			} else {
				// System.out.println("string not found");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
