package mul.com.sns.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchParam {

	private String search;
	private String choice;
	
	private int currentPage;
	private int dataPerPage;
	private int start;
	private int end;
	
	private boolean sns_only;
	private int answerStatus;
	
	private String startDate;
	private String endDate;
}
