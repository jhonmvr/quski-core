package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class NodoWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
		/**
		 * 
		 */
		
		private String Name;
		private String Filetype;
		private String DateCreated;
		private String DateModified;
		private String Type;
		private String Size;
		private String Comment;
		private String ReferenceObjectId;
		private String ReferenceCollection;
		private String ParentId;
		private String FileBase64;
		private List<UsuarioWrapper> Users;
		private List<PerfilesWrapper> Profiles;
		private List<VersionWrapper> Version;
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getFiletype() {
			return Filetype;
		}
		public void setFiletype(String filetype) {
			Filetype = filetype;
		}
		public String getDateCreated() {
			return DateCreated;
		}
		public void setDateCreated(String dateCreated) {
			DateCreated = dateCreated;
		}
		public String getDateModified() {
			return DateModified;
		}
		public void setDateModified(String dateModified) {
			DateModified = dateModified;
		}
		public String getType() {
			return Type;
		}
		public void setType(String type) {
			Type = type;
		}
		public String getSize() {
			return Size;
		}
		public void setSize(String size) {
			Size = size;
		}
		public String getComment() {
			return Comment;
		}
		public void setComment(String comment) {
			Comment = comment;
		}
		public String getReferenceObjectId() {
			return ReferenceObjectId;
		}
		public void setReferenceObjectId(String referenceObjectId) {
			ReferenceObjectId = referenceObjectId;
		}
		public String getReferenceCollection() {
			return ReferenceCollection;
		}
		public void setReferenceCollection(String referenceCollection) {
			ReferenceCollection = referenceCollection;
		}
		public String getParentId() {
			return ParentId;
		}
		public void setParentId(String parentId) {
			ParentId = parentId;
		}
		public String getFileBase64() {
			return FileBase64;
		}
		public void setFileBase64(String fileBase64) {
			this.FileBase64 = fileBase64;
		}
		public List<UsuarioWrapper> getUsers() {
			return Users;
		}
		public void setUsers(List<UsuarioWrapper> users) {
			Users = users;
		}
		public List<PerfilesWrapper> getProfiles() {
			return Profiles;
		}
		public void setProfiles(List<PerfilesWrapper> profiles) {
			Profiles = profiles;
		}
		public List<VersionWrapper> getVersion() {
			return Version;
		}
		public void setVersion(List<VersionWrapper> version) {
			Version = version;
		}

	/**
	 * 
	 */
	
		
	
	


	
}
