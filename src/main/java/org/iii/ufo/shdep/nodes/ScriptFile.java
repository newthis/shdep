package org.iii.ufo.shdep.nodes;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;

import org.iii.ufo.shdep.Shell;
import org.json.JSONObject;

//The class is exactly like 'Block' ?
public class ScriptFile implements Node{
	
	private final StmtList stmtList;


	public ScriptFile(JSONObject obj) {
		stmtList = new StmtList(obj.getJSONArray("Stmts"));
		//stmtList = new StmtList(obj.getJSONObject("StmtList"));
	}
	
	
	public StmtList getStmtList() {
		return stmtList;
	}

	//helper to forward visitor
	public void accept(NodeVisitor visitor){
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return stmtList.toString("\n");
	}


    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

	public static void main(String [] args) {
	    System.out.println("Hello");
	    String content = readFileContent("D:\\code_base\\ts.txt");
	    JSONObject tst = new JSONObject(content);
	    Path p = null;
        Shell sh = new Shell(p);
        Node sf = new ScriptFile(tst);
        sh._run(null, sf, Collections.emptyList());
//        StmtList sl = sf.getStmtList();
//        Iterator<Stmt> ite = sl.iterator();
//        while(ite.hasNext()){
//            Stmt stmt = ite.next();
//            System.out.println(stmt.getCmd());
//        }
        //System.out.println(sl.isEmpty());

    }

}
