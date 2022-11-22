package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.donation.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/homepage", new ForwardController("/homepage.jsp"));
        mappings.put("/donationList/donationFeed", new DonationArticleController()); // 1123 by 채연
        
        //mypage section
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/myInfo", new UserInfoContoller());
        mappings.put("/user/delete", new DeleteUserController());
        mappings.put("/user/myArticle", new UserArticleController());
        mappings.put("/user/myDonaion", new UserDonationController());
        
        //donation Article Create
        mappings.put("/donationForm/animal", new AnimalArticleController());
        mappings.put("/donationForm/disaster", new DisasterArticleController());
        mappings.put("/donationForm/socialGroup", new SocialGroupArticleController());
        
        // donation Article View
        mappings.put("/donationList/animal", new ViewAnimalArticleController()); // 1123 by 채연
        
        //donation Article Update
        mappings.put("/donationList/animalArticleUpdate", new UpdateAnimalArticleController());
        
        // comment controller // 1123 by 채연
        mappings.put("/donationList/comment", new CommentController());
        mappings.put("/donationList/commentDelete", new DeleteCommentController());
        mappings.put("/donationList/commentUpdate", new UpdateCommentController());

        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}