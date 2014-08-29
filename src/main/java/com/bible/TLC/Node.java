package com.bible.TLC;

import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class Node {
	
	private LinkedList<Node> children;
	private Node father;
	private long father_id;
	private long left_child_id;
	private long right_child_id;
	private long database_id;
	private int absolute_start_offset;
	private int absolute_end_offset;
	private String structure_key;
	private int order;
	
	public Node(Node father, int absolute_start_offset, int order, String structure_key){
		this.children=new LinkedList<Node>();
		this.absolute_start_offset=absolute_start_offset;
		this.order=order;
		this.father=father;
		this.structure_key=structure_key;
		if (this.father!=null) this.father.addChild(this);
	}
	
	public Node(int absolute_start_offset, int order){
		this.children=new LinkedList<Node>();
		this.absolute_start_offset=absolute_start_offset;
		this.order=order;
		this.father=null;
		this.structure_key=null;
	}
	
	
	
	public List<Node> getChildren(){
		return this.children;
	}
	
	public void setChildIds(){
		try{
			this.left_child_id=children.getFirst().database_id;
			this.right_child_id=children.getLast().database_id;
		}
		catch (NoSuchElementException e){
			this.left_child_id=this.right_child_id=this.database_id;
		}
	}
	
	public long getLeft_child_id(){
		return this.left_child_id;
	}
	
	public long getRight_child_id(){
		return this.right_child_id;
	}
	
	public int getOrdering(){
		return this.order;
	}
		
	public void setAbsolute_end_offset(int absolute_end_offset){
		this.absolute_end_offset=absolute_end_offset;
	}
	
	public int getAbsolute_end_offset(){
		return this.absolute_end_offset;
	}
	
	public int getAbsolute_start_offset(){
		return this.absolute_start_offset;
	}
	
	
	public void setDatabase_id(long database_id){
		this.database_id=database_id;
	}
	
	public void addChild(Node node){
		children.add(node);
	}
	
	public Node getFather(){
		return father;
	}
	
	public long getFather_id(){
		return father_id;
	}
	
	public long getDatabase_id(){
		return database_id;
	}
	
	public String getStructure_key(){
		return structure_key;
	}
	
	public void setStructure_key(String structure_key){
		this.structure_key=structure_key;
	}

	public void setFather_id(long father_id){
		this.father_id=father_id;
	}
	
	public void setLeft_child_id(long left_child_id){
		this.left_child_id=left_child_id;
	}
	
	public void setRight_child_id(long right_child_id){
		this.right_child_id=right_child_id;
	}

	@Override
	public String toString() {
		return "Node [children=" + children.size() + ", father=" + father
				+ ", father_id=" + father_id + ", left_child_id="
				+ left_child_id + ", right_child_id=" + right_child_id
				+ ", database_id=" + database_id + ", absolute_start_offset="
				+ absolute_start_offset + ", absolute_end_offset="
				+ absolute_end_offset + ", structure_key=" + structure_key
				+ ", order=" + order + "]";
	}

	
		
}
