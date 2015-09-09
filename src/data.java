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
		 System.out.println(str);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			OutputStream os;
			ByteArrayOutputStream bout;
			DataOutputStream dout;

			data read = new data();

			String path = "/Users/zhusu/Desktop/3DScene/json/";
			
			String outpath = "/Users/zhusu/Desktop/3DScene/out/";

			File dir = new File(path);

			String[] dirs = dir.list();

			for (int k = 0; k < dirs.length; ++k) {
				if (dirs[k].endsWith(".json")) {

					String JsonContext = read.ReadFile(path + dirs[k]);

					JSONObject jsonObject = JSONObject.fromObject(JsonContext);
					
					
					JSONObject Content =  jsonObject.getJSONObject("Content").getJSONObject("Content");
					
					JSONObject ObjectData = Content.getJSONObject("ObjectData");
					
					JSONArray Children  =  ObjectData.getJSONArray("Children");
					
					os = new FileOutputStream(new File(outpath
							+ dirs[k].replaceFirst(".json", ".data")));

					bout = new ByteArrayOutputStream();
					dout = new DataOutputStream(bout);

					int num = 0;
					
					num = Children.size();
					
					read.log("num: " + num);
					
					dout.writeInt(num);

					int writeNum = 0;
					
					for (int i = 0; i < Children.size(); ++i) {

						JSONObject jdata = Children.getJSONObject(i);
						
						if(jdata.getString("ctype").equals("UserCameraObjectData")){
							
							writeNum++;
							
							int type = 1;
							read.log("type: " + type);
							
							dout.writeInt(type);
							
							String name = jdata.getString("Name");
							read.log("name: "+name);
							dout.writeUTF(name);
							
							float fov = (float) jdata.getDouble("Fov");
							read.log("fov: "+fov);
							dout.writeFloat(fov);
							
							JSONObject jdata1 = jdata.getJSONObject("ClipPlane");
							
							float ClipPlaneX = (float) jdata1.getDouble("X");
							
							read.log("ClipPlaneX: "+ClipPlaneX);
							
							dout.writeFloat(ClipPlaneX);
							
							float ClipPlaneY = (float) jdata1.getDouble("Y");
							
							read.log("ClipPlaneY: "+ClipPlaneY);
							
							dout.writeFloat(ClipPlaneY);
							
							
							int CameraFlagMode = jdata.getInt("CameraFlagMode");
							
							read.log("CameraFlagMode: "+CameraFlagMode);
							
							dout.writeInt(CameraFlagMode);
							
							JSONObject jdata2 = jdata.getJSONObject("Position3D");
							
							float Position3DX =(float) jdata2.getDouble("X");
							
							read.log("Position3DX: "+Position3DX);
							
							dout.writeFloat(Position3DX);
							
							float Position3DY =(float) jdata2.getDouble("Y");
							
							read.log("Position3DY: "+Position3DY);
							
							dout.writeFloat(Position3DY);
							
							float Position3DZ =(float) jdata2.getDouble("Z");
							
							read.log("Position3DZ: "+Position3DZ);
							
							dout.writeFloat(Position3DZ);
							
							JSONObject jdata3 = jdata.getJSONObject("Rotation3D");
							
							float Rotation3DX = (float) jdata3.getDouble("X");
							
							read.log("Rotation3DX: "+Rotation3DX);
							
							dout.writeFloat(Rotation3DX);
							
							float Rotation3DY = (float) jdata3.getDouble("Y");
							
							read.log("Rotation3DY: "+Rotation3DY);
							
							dout.writeFloat(Rotation3DY);
							
							float Rotation3DZ = (float) jdata3.getDouble("Z");
							
							read.log("Rotation3DZ: "+Rotation3DZ);
							
							dout.writeFloat(Rotation3DZ);
							
							
						}else if(jdata.getString("ctype").equals("Sprite3DObjectData")){
							writeNum++;
							
							int type = 2;
							read.log("type: " + type);
							
							dout.writeInt(type);
							
							String name = jdata.getString("Name");
							read.log("name: "+name);
							dout.writeUTF(name);
							
							String Path = jdata.getJSONObject("FileData").getString("Path");
							
							read.log("Path: "+Path);
							dout.writeUTF(Path);
							
							int CameraFlagMode = jdata.getInt("CameraFlagMode");
							
							read.log("CameraFlagMode: "+CameraFlagMode);
							
							dout.writeInt(CameraFlagMode);
							
							int Tag = jdata.getInt("Tag");
							
							read.log("Tag: "+Tag);
							
							dout.writeInt(Tag);
							
							boolean isrun = jdata.getBoolean("RunAction3D");
							
							read.log("isrun: "+isrun);
							
							dout.writeBoolean(isrun);
							
							JSONObject jdata2 = jdata.getJSONObject("Position3D");
							
							float Position3DX =(float) jdata2.getDouble("X");
							
							read.log("Position3DX: "+Position3DX);
							
							dout.writeFloat(Position3DX);
							
							float Position3DY =(float) jdata2.getDouble("Y");
							
							read.log("Position3DY: "+Position3DY);
							
							dout.writeFloat(Position3DY);
							
							float Position3DZ =(float) jdata2.getDouble("Z");
							
							read.log("Position3DZ: "+Position3DZ);
							
							dout.writeFloat(Position3DZ);
							
							JSONObject jdata3 = jdata.getJSONObject("Rotation3D");
							
							float Rotation3DX = (float) jdata3.getDouble("X");
							
							read.log("Rotation3DX: "+Rotation3DX);
							
							dout.writeFloat(Rotation3DX);
							
							float Rotation3DY = (float) jdata3.getDouble("Y");
							
							read.log("Rotation3DY: "+Rotation3DY);
							
							dout.writeFloat(Rotation3DY);
							
							float Rotation3DZ = (float) jdata3.getDouble("Z");
							
							read.log("Rotation3DZ: "+Rotation3DZ);
							
							dout.writeFloat(Rotation3DZ);
							
							JSONObject jdata4 = jdata.getJSONObject("Scale3D");
							
							float Scale3DX = (float) jdata4.getDouble("X");
							
							read.log("Scale3DX: "+Scale3DX);
							
							dout.writeFloat(Scale3DX);
							
							float Scale3DY = (float) jdata4.getDouble("Y");
							
							read.log("Scale3DY: "+Scale3DY);
							
							dout.writeFloat(Scale3DY);
							
							float Scale3DZ = (float) jdata4.getDouble("Z");
							
							read.log("Scale3DZ: "+Scale3DZ);
							
							dout.writeFloat(Scale3DZ);
							
						}else if(jdata.getString("ctype").equals("Node3DObjectData")){
							writeNum++;
							
							int type = 3;
							read.log("type: " + type);
							
							dout.writeInt(type);
							
							String name = jdata.getString("Name");
							read.log("name: "+name);
							dout.writeUTF(name);
							
							int CameraFlagMode = jdata.getInt("CameraFlagMode");
							
							read.log("CameraFlagMode: "+CameraFlagMode);
							
							dout.writeInt(CameraFlagMode);
							
							int Tag = jdata.getInt("Tag");
							
							read.log("Tag: "+Tag);
							
							dout.writeInt(Tag);
							
							JSONObject jdata2 = jdata.getJSONObject("Position3D");
							
							float Position3DX =(float) jdata2.getDouble("X");
							
							read.log("Position3DX: "+Position3DX);
							
							dout.writeFloat(Position3DX);
							
							float Position3DY =(float) jdata2.getDouble("Y");
							
							read.log("Position3DY: "+Position3DY);
							
							dout.writeFloat(Position3DY);
							
							float Position3DZ =(float) jdata2.getDouble("Z");
							
							read.log("Position3DZ: "+Position3DZ);
							
							dout.writeFloat(Position3DZ);
							
							JSONObject jdata3 = jdata.getJSONObject("Rotation3D");
							
							float Rotation3DX = (float) jdata3.getDouble("X");
							
							read.log("Rotation3DX: "+Rotation3DX);
							
							dout.writeFloat(Rotation3DX);
							
							float Rotation3DY = (float) jdata3.getDouble("Y");
							
							read.log("Rotation3DY: "+Rotation3DY);
							
							dout.writeFloat(Rotation3DY);
							
							float Rotation3DZ = (float) jdata3.getDouble("Z");
							
							read.log("Rotation3DZ: "+Rotation3DZ);
							
							dout.writeFloat(Rotation3DZ);
							
							
							JSONObject jdata4 = jdata.getJSONObject("Scale3D");
							
							float Scale3DX = (float) jdata4.getDouble("X");
							
							read.log("Scale3DX: "+Scale3DX);
							
							dout.writeFloat(Scale3DX);
							
							float Scale3DY = (float) jdata4.getDouble("Y");
							
							read.log("Scale3DY: "+Scale3DY);
							
							dout.writeFloat(Scale3DY);
							
							float Scale3DZ = (float) jdata4.getDouble("Z");
							
							read.log("Scale3DZ: "+Scale3DZ);
							
							dout.writeFloat(Scale3DZ);
						}

					}
					
					
					read.log("writeNum: "+writeNum);
					
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
