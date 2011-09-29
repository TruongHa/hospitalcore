package org.openmrs.module.hospitalcore.concept;

public class Mapping implements Comparable<Mapping> {
	private String name;
	private String source;
	private String sourceCode;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result
				+ ((sourceCode == null) ? 0 : sourceCode.hashCode());
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
		Mapping other = (Mapping) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (sourceCode == null) {
			if (other.sourceCode != null)
				return false;
		} else if (!sourceCode.equals(other.sourceCode))
			return false;
		return true;
	}
	
	public int compareTo(Mapping o) {
		String mt = this.getName() + this.getSource() + this.getSourceCode();
		String ot = o.getName() + o.getSource() + o.getSourceCode();
		return mt.compareTo(ot);
	}
}
