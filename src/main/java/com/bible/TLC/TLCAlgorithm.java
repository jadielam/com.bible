package com.bible.TLC;

import java.util.HashMap;
import java.util.LinkedList;


public class TLCAlgorithm {
		
		
	/**
	 * Parses the text according to the hierarchy defined by hierarchy,
	 * and returns the root node of the document tree. 
	 * @param text
	 * @return
	 */
	
		
	//TODO: Implement the throwing of the right exception here.
	public Node parseText(String text) throws Exception {
	
		//0. Reading the ordering from the text
		HashMap<String, Integer> ordering=readOrdering(text);
		//1. Building the document tree
		
		final int CHAPTER_ORDERING = ordering.get("chapter");
		final int BOOK_ORDERING	= ordering.get("book");
		final int VERSE_ORDERING = ordering.get("verse");
		
		Node root_node=new Node(0, 0);
		Node parent_node=root_node;
		Node previous_node=root_node;
		Node current_node=root_node;
		int start_offset=text.indexOf("<div", 0);
		int node_start_offset=start_offset;
		
		while (start_offset!=-1){
			
			int b=text.indexOf("\"", start_offset+12);
			int div_order=ordering.get(text.substring(start_offset+12, b));
			int c=text.indexOf("title", start_offset);
			int d=text.indexOf("\"", c+7);
			String title_text=text.substring(c+7, d);
			
			if (div_order==previous_node.getOrdering()){
				previous_node.setAbsolute_end_offset(start_offset);
			}
			else if (div_order<previous_node.getOrdering()){
				Node temp_node=previous_node;
				while (temp_node!=null && div_order<=temp_node.getOrdering()){
					temp_node.setAbsolute_end_offset(start_offset);
					temp_node=temp_node.getFather();
					
				}
				parent_node=temp_node;
			}
			else{
				parent_node=previous_node;
				node_start_offset=previous_node.getAbsolute_start_offset();
			}
			
			current_node=new Node(parent_node, node_start_offset, div_order, title_text);
			previous_node=current_node;
			start_offset=text.indexOf("<div", start_offset+1);
			node_start_offset=start_offset;
		}
		
		int end_offset=text.lastIndexOf("</div>")+6;
		
		Node temp_node=previous_node;
		
		while (temp_node!=null){
			temp_node.setAbsolute_end_offset(end_offset);
			temp_node=temp_node.getFather();
		}
		
		//2. Updating the database_id and father_id
		//Do this using a BFS approach.
		
		LinkedList<Node> queue=new LinkedList<Node>();
		long database_id=0;
		queue.push(root_node);
		while (!queue.isEmpty()){
			
			Node node=queue.pollFirst();
			node.setDatabase_id(database_id);
			database_id+=1;
			try{
				node.setFather_id(node.getFather().getDatabase_id());
			}
			catch (NullPointerException e){
				node.setFather_id(node.getDatabase_id());
			}
			
			for (Node child_node : node.getChildren()){
				queue.add(child_node);
			}
		}
		
		//3. Updating the left_child_id and the right_child_id
		queue.clear();
		queue.push(root_node);
		while (!queue.isEmpty()){
			
			Node node=queue.pollFirst();
			node.setChildIds();
			
			for (Node child_node : node.getChildren()){
				queue.add(child_node);
			}
		}
		
		//4. Updating the title field
		queue.clear();
		queue.push(root_node);
		LinkedList<Node> second_queue=new LinkedList<Node>();
		while (!queue.isEmpty()){
			Node node=queue.pollFirst();
			second_queue.addFirst(node);
							
			for (Node child_node : node.getChildren()){
				queue.add(child_node);
			}
		}
		
		for (Node node : second_queue){
		
			Node temp_parent_node=node.getFather();
			LinkedList<String> title=new LinkedList<String>();
			title.addFirst(node.getStructure_key());
			while (temp_parent_node!=null && temp_parent_node.getStructure_key()!=null){
				title.addFirst(temp_parent_node.getStructure_key());
				temp_parent_node=temp_parent_node.getFather();
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("(");
			
			int counter=0;
			for (String section : title){
				sb.append(section);
				if (counter!=title.size()-1){
					sb.append(",");
				}
				
				counter+=1;
			}
			sb.append(")");
			
			node.setStructure_key(sb.toString());
		}
		return root_node;
	}
	
	private HashMap<String, Integer> readOrdering(String text){
		
		int a=text.indexOf("<structure>");
		int b=text.indexOf("</structure>");
		
		String ordering_s=text.substring(a+11, b);
		HashMap<String, Integer> ordering=new HashMap<String, Integer>();
		ordering_s=ordering_s.substring(1, ordering_s.length()-1);
		String [] orders=ordering_s.split(",");
		for (int i=0; i<orders.length; ++i){
			String order=orders[i].trim();
			ordering.put(order,i);
		}
		
		return ordering;
		
	}
}