package org.iii.ufo.shdep.nodes.cmd;

import org.iii.ufo.shdep.nodes.NodeVisitor;
import org.iii.ufo.shdep.nodes.StmtList;
import org.json.JSONObject;
import org.json.JSONArray;

public class IfClause implements Command{
	private StmtList cond;
	private StmtList then;
	private StmtList else_;

	public IfClause(JSONObject obj){
		if(!obj.get("Cond").toString().equals("null")){
			//ok
			this.cond = new StmtList(obj.getJSONArray("Cond"));
		}
		if(!obj.get("Then").toString().equals("null")){
			//ok
			this.then = new StmtList(obj.getJSONArray("Then"));
		}
		if(!obj.get("Else").toString().equals("null")){
			JSONObject subElse = obj.getJSONObject("Else");
			if(subElse.keySet().contains("Then")){
				JSONArray thenArray = subElse.getJSONArray("Then");
				this.else_ = new StmtList(thenArray);
			}
		}
		//this.cond = new StmtList(obj.getJSONObject("Cond"));
		//this.then = new StmtList(obj.getJSONObject("Then"));
		//this.else_ = new StmtList(obj.getJSONObject("Else"));
	}

	public StmtList getCond() {
		return cond;
	}

	public StmtList getThen() {
		return then;
	}

	public StmtList getElse() {
		return else_;
	}
	
	@Override
	public void accept(NodeVisitor visitor){
		visitor.visit(this);
	}

	@Override
	public String toString(){
		String thenBlock = "";
		String elseBlock = "";
		if(then != null){
			thenBlock = INDENT + then.toString("\n").replaceAll("\n", "\n" + INDENT);
		}
		if(else_ != null){
			elseBlock = INDENT + else_.toString("\n").replaceAll("\n", "\n" + INDENT);
		}
		return String.format("if %s; then\n%s%s\nfi",
					cond.toString(),
					thenBlock,
				    (else_ == null||else_.isEmpty())? "": "\nelse\n" + elseBlock);
	}
}
