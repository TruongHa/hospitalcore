package org.openmrs.module.hospitalcore.util;

import java.util.Comparator;

import org.openmrs.Concept;

public class ConceptComparator implements Comparator<Concept>{

	public int compare(Concept o1, Concept o2) {
		return o1.getName().getName().compareToIgnoreCase(o2.getName().getName());
	}

}
