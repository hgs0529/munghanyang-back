package mul.com.sns.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mul.com.sns.dto.FollowDto;
import mul.com.sns.service.FollowService;

@RestController
public class FollowController {
	
	@Autowired
	FollowService followservice;
	
	// 팔로우
	@PostMapping(value = "/follow")
	public String follow(FollowDto followdto) {
	
	followservice.follow(followdto);
	
	return "FollowOK";
	}
	
	// 언팔로우
	@PostMapping(value = "/unfollow")
	public String unfollow(FollowDto followdto) {
	
	followservice.unfollow(followdto);
	
	return "UnFollowOK";
	}
	
	// 팔로워, 팔로잉 리스트 조회
	@RequestMapping(value = "/followlist", method = RequestMethod.GET)
	public Map<String, List<FollowDto>> personalList(FollowDto followdto) throws Exception {
		
		Map<String, List<FollowDto>> map = new HashMap<>();
		
		List<FollowDto> followerList = followservice.FollowgiveList(followdto);
		List<FollowDto> followingList = followservice.FollowrecvList(followdto);
		
	//	List<FollowDto> followerList = new ArrayList<FollowDto>();
	//	followerList.add(followdto);
		
		map.put("followerList", followerList);
		map.put("followingList", followingList);
				
		return map;
	}
	
}


/*
<script type="text/javascript">
$('#follow_btn').on('click', function() {
	follow(true);
});

$('#unfollow_btn').on('click', function() {
	follow(false);
});

function follow(check) {
	if(check) {
		$.ajax({
			type:"POST",
			url:"http://http://localhost:3000/follow",
			success: function(result) {
				if(result == "FollowOK"){
					$(".follow").html('<button class="followBtn" id="unfollow_btn">언팔로우</button>');
				//	location.href="";
				}
			}
		});
	} else {
		$.ajax({
			type:"POST",
			url:"http://http://localhost:3000/unfollow",
			success: function(result) {
				if(result == "UnFollowOK"){
					$(".follow").html('<button class="followBtn" id="follow_btn">팔로우</button>');
				}
			}
		});
	}
}

</script>
*/
