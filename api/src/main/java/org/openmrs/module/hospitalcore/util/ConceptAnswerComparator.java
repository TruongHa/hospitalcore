package org.openmrs.module.hospitalcore.util;

import java.util.Comparator;

import org.openmrs.ConceptAnswer;

public class ConceptAnswerComparator implements Comparator<ConceptAnswer>
{

	public int compare(ConceptAnswer o1, ConceptAnswer o2) {
		return o1.getAnswerConcept().getName().getName().compareTo(o2.getAnswerConcept().getName().getName());
	}




}
