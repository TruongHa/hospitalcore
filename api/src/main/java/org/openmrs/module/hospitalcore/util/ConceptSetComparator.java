package org.openmrs.module.hospitalcore.util;

import java.util.Comparator;

import org.openmrs.ConceptSet;

public class ConceptSetComparator implements Comparator<ConceptSet>
{

	public int compare(ConceptSet o1, ConceptSet o2) {
		return o1.getConcept().getName().getName().compareTo(o2.getConcept().getName().getName());
	}


}
