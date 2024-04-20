//package reservpurchase.service.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.event.annotation.BeforeTestMethod;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.Errors;
//import reservpurchase.service.dto.MemberDto;
//import reservpurchase.service.entity.embeded.Address;
//import reservpurchase.service.vo.request.RequestMember;
//import reservpurchase.service.vo.response.ResponseMember;
//import reservpurchase.service.vo.response.auth.ResponseVo;
//
//@SpringBootTest
//@Transactional
//class MemberControllerTest {
//
//    @Autowired
//    MemberController memberController;
//
//    @PersistenceContext
//    EntityManager em;
//
//
//
//    @Test
//    @DisplayName("주소가 없을 때, RequestMember -> MemberDto 매핑 되는지")
//    void 주소없을때() {
//        //given
//        RequestMember requestMember = new RequestMember();
//        requestMember.setPassword("12341234");
//        requestMember.setEmail("dbdb1114213");
//        requestMember.setName("유정현");
//        requestMember.setPhone("11111111111");
//
//        RequestMember requestMemberAdd = new RequestMember();
//        requestMemberAdd.setPassword("12341231234");
//        requestMemberAdd.setEmail("dbdb1112314213");
//        requestMemberAdd.setName("유정현123");
//        requestMember.setPhone("00000000000");
//
//        new Address("asdfasdf",  "asdfsadfsadf");
//
//        //when
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        MemberDto member = mapper.map(requestMember, MemberDto.class);
//        MemberDto addMember = mapper.map(requestMemberAdd, MemberDto.class);
//
//        System.out.println(addMember);
//
//        //then
//        assertThat(member).isNotNull();
//        assertThat(addMember.getName()).isEqualTo("유정현123");
//    }
//
//
//    @Test
//    void join() throws Exception {
//        //given
//        RequestMember requestMember = new RequestMember("dbdb1114@naver.com","유정현","test1234","123456789",
//                new Address("성남시 중원구 하대원동", "성원초원 아파트 103동 1007호"));
//        ResponseEntity<ResponseVo> join =  memberController.join(requestMember, new Errors() {
//        });
//        em.flush();
//
//        //when
//
//        RequestMember DPMember = new RequestMember("dbdb1111234@naver.com","유정현","test1234","123456789",
//                new Address("성남시 중원구 하대원동", "성원초원 아파트 103동 1007호"));
//        ResponseEntity<ResponseVo> DP =  memberController.join(DPMember, new Error());
//
//        //then
//        assertThat(join.getBody().getClass()).isEqualTo(ResponseMember.class);
//        assertThat(DP.getBody().getResponseCode()).isEqualTo("DP");
//    }
//
//    @BeforeTestMethod("중복_메세지_확인")
//    void 미리저장() throws Exception {
//        RequestMember requestMember = new RequestMember("dbdb1114@naver.com","유정현","test1234","123456789",
//                new Address("성남시 중원구 하대원동", "성원초원 아파트 103동 1007호"));
//        ResponseEntity<ResponseVo> join =  memberController.join(requestMember, new Error());
//    }
//    @Test
//    void 중복_메세지_확인() throws Exception {
//        //given
//        RequestMember requestMember = new RequestMember("dbdb1111234@naver.com","유정현","test1234","123456789",
//                new Address("성남시 중원구 하대원동", "성원초원 아파트 103동 1007호"));
//
//        //when
//        ResponseEntity<ResponseVo> join =  memberController.join(requestMember, new Error());
//        System.out.println("join = " + join);
//        //then
//        assertThat(join.getBody().getResponseCode()).isEqualTo("DP");
//    }
//}
