import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import sun.applet.Main;

public class data {

	public String ReadFile(String Path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(
					fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

	public void log(String str) {
		// System.out.println(str);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			OutputStream os;
			ByteArrayOutputStream bout;
			DataOutputStream dout;

			data read = new data();

			String path = "/Users/zhusu/Desktop/data/";

			File dir = new File(path);

			String[] dirs = dir.list();

			for (int k = 0; k < dirs.length; ++k) {
				if (dirs[k].endsWith(".json")) {

					String JsonContext = read.ReadFile(path + dirs[k]);

					JSONObject jsonObject = JSONObject.fromObject(JsonContext);

					JSONObject Content = jsonObject.getJSONObject("Content")
							.getJSONObject("Content");

					JSONObject ObjectData = Content.getJSONObject("ObjectData");

					JSONArray Children = ObjectData.getJSONArray("Children");

					os = new FileOutputStream(new File(path
							+ dirs[k].replaceFirst(".json", ".data")));

					bout = new ByteArrayOutputStream();
					dout = new DataOutputStream(bout);

					int num = 0;

					for (int i = 0; i < Children.size(); ++i) {
						JSONObject jdata = Children.getJSONObject(i);
						if (jdata.getString("ctype").equals(
								"ArmatureNodeObjectData")) {
							num++;
						}
					}

					read.log("size: " + num);
					dout.writeInt(num);

					int endY = 0;

					for (int i = 0; i < Children.size(); ++i) {

						JSONObject jdata = Children.getJSONObject(i);

						if (jdata.getString("ctype").equals(
								"ArmatureNodeObjectData")) {

							String name = jdata.getJSONObject("FileData")
									.getString("Path");

							name = name.replaceFirst(".ExportJson", ".csb");

							read.log("Path: " + name);

							dout.writeUTF(name);

							read.log("IsLoop: " + jdata.getBoolean("IsLoop"));

							dout.writeBoolean(jdata.getBoolean("IsLoop"));

							read.log("CurrentAnimationName: "
									+ jdata.getString("CurrentAnimationName"));

							dout.writeUTF(jdata
									.getString("CurrentAnimationName"));

							read.log("X: "
									+ jdata.getJSONObject("Position").getInt(
											"X"));

							dout.writeInt(jdata.getJSONObject("Position")
									.getInt("X"));

							read.log("Y: "
									+ jdata.getJSONObject("Position").getInt(
											"Y"));

							dout.writeInt(jdata.getJSONObject("Position")
									.getInt("Y"));

							read.log("ScaleX: "
									+ jdata.getJSONObject("Scale").getDouble(
											"ScaleX"));

							dout.writeFloat((float) jdata
									.getJSONObject("Scale").getDouble("ScaleX"));

							read.log("ScaleY: "
									+ jdata.getJSONObject("Scale").getDouble(
											"ScaleY"));

							dout.writeFloat((float) jdata
									.getJSONObject("Scale").getDouble("ScaleY"));

							float rotation = 0;
							if (jdata.has("Rotation")) {
								rotation = (float) jdata.getDouble("Rotation");
							}

							read.log("Rotation: " + rotation);
							dout.writeFloat(rotation);

							read.log("IconVisible: "
									+ jdata.getBoolean("IconVisible"));

							dout.writeBoolean(jdata.getBoolean("IconVisible"));

							String userData = "";

							if (jdata.has("UserData")) {
								userData = jdata.getString("UserData");
							}

							read.log("UserData: " + userData);

							dout.writeUTF(userData);

							read.log("Tag: " + jdata.getInt("Tag"));

							dout.writeInt(jdata.getInt("Tag"));

							read.log("Name: " + jdata.getString("Name"));

							dout.writeUTF(jdata.getString("Name"));

						} else if (jdata.getString("ctype").equals(
								"SingleNodeObjectData")
								&& jdata.getInt("Tag") == -10) {

							endY = jdata.getJSONObject("Position").getInt("Y");

						}

					}

					read.log("endY: " + endY);

					dout.writeInt(endY);

					dout.flush();
					bout.flush();

					os.write(bout.toByteArray());

					dout.close();
					bout.close();
					os.close();
					System.out.println("ok!!!  " + dirs[k]);

				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}
}
