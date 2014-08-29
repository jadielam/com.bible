package com.bible.TLC;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class TLCAlgorithmTest {

	public static void main(String[] args) {
		
		//String filePath=args[0];
		
		String filePath="/home/jadiel/workspace/bible_site/treeparsing/kjv.html";
		
		try {
			String text=Files.toString(new File(filePath), Charsets.UTF_8);
			TLCAlgorithm parser=new TLCAlgorithm();
			Node root_node=parser.parseText(text);
			System.out.println(root_node);
			List<Node> nodes=root_node.getChildren();
			for (Node node : nodes){
				List<Node> nodes_1=node.getChildren();
				for (Node node1 : nodes_1){
					System.out.println(node1);
					List<Node> nodes_2=node1.getChildren();
					for (Node node2 : nodes_2){
						System.out.println(node2);
					}
				}
			}
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		//Exception with malformed document
		catch (Exception e){
			e.printStackTrace();
		}
		
			
	}

}
