package org.openmrs.module.hospitalcore.concept;

import java.util.Set;
import java.util.TreeSet;

import org.openmrs.Concept;

public class ConceptNode implements Comparable<ConceptNode> {

	private Concept concept;
	private Set<ConceptNode> childNodes = new TreeSet<ConceptNode>();
	private ConceptNode parent;

	public ConceptNode() {

	}

	public ConceptNode(Concept concept) {
		this.concept = concept;
	}

	public ConceptNode(Concept concept, ConceptNode parent) {
		this.concept = concept;
		this.parent = parent;
	}

	public int compareTo(ConceptNode o) {
		String mName = concept.getName().getName();
		String oName = o.getConcept().getName().getName();
		return mName.compareToIgnoreCase(oName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concept == null) ? 0 : concept.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConceptNode other = (ConceptNode) obj;
		if (concept == null) {
			if (other.concept != null)
				return false;
		} else if (!concept.equals(other.concept))
			return false;
		return true;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public Set<ConceptNode> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(Set<ConceptNode> childNodes) {
		this.childNodes = childNodes;
	}

	public ConceptNode getParent() {
		return parent;
	}

	public void setParent(ConceptNode parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "ConceptNode [conceptId=" + concept.getConceptId() + "]";
	}
	
	
}
