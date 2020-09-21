package org.iii.ufo.shdep.nodes.test;
import org.iii.ufo.shdep.nodes.Node;
import org.iii.ufo.shdep.nodes.Stmt;
import org.iii.ufo.shdep.nodes.StmtList;
import org.iii.ufo.shdep.nodes.cmd.IfClause;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Iterator;

import org.iii.ufo.shdep.nodes.cmd.Command;
public class Tests {
    public static String readToBuffer(String filePath) throws IOException {
        StringBuffer buffer = new StringBuffer();
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
        return buffer.toString();
    }

    public static void main(String [] args) {
        try{
            String content = readToBuffer("D:\\vmware_mache_data_share\\netgear_test\\130\\log.txt");
            JSONObject job = new JSONObject(content);
            JSONArray jay = job.getJSONArray("Stmts");
            JSONObject jsonObject = (JSONObject) jay.get(0);
            JSONObject cmds  = jsonObject.getJSONObject("Cmd");
            IfClause ifClause = new IfClause(cmds);
            System.out.println(ifClause.toString());
//            StmtList stmtList = new StmtList(job.getJSONArray("Stmts"));
//            Iterator<Stmt> ite = stmtList.iterator();
//            while(ite.hasNext()){
//                Stmt stmt = ite.next();
//                System.out.println(stmt.toString());
//            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
