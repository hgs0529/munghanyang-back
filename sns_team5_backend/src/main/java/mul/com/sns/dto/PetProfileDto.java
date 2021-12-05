package mul.com.sns.dto;

import java.io.Serializable;

public class PetProfileDto implements Serializable {
	
	private int seq;
	private int userid;
	private String photo;
	private int neuter;
	private String weight;
	private String name;
	private String breed;
	private String gender;
	private String birth;
	private String content;
	
	public PetProfileDto() {
	}

	public PetProfileDto(int seq, int userid, String photo, int neuter, String weight, String name, String breed,
			String gender, String birth, String content) {
		super();
		this.seq = seq;
		this.userid = userid;
		this.photo = photo;
		this.neuter = neuter;
		this.weight = weight;
		this.name = name;
		this.breed = breed;
		this.gender = gender;
		this.birth = birth;
		this.content = content;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getNeuter() {
		return neuter;
	}

	public void setNeuter(int neuter) {
		this.neuter = neuter;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "PetProfileDto [seq=" + seq + ", userid=" + userid + ", photo=" + photo + ", neuter=" + neuter
				+ ", weight=" + weight + ", name=" + name + ", breed=" + breed + ", gender=" + gender + ", birth="
				+ birth + ", content=" + content + "]";
	}

	
}
