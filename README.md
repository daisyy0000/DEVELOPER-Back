# DEVELOPER : IT 자기 개발 서비스 중개 플랫폼
--------------------

### [배경]  🔦 
엠브레인 트렌드 모니터의 조사에 따르면 2030 조기 퇴사자들의 대부분은 <strong>‘직무에 대한 비전’</strong>과  <strong>‘미래 지향성’ 부족’</strong>을 퇴사 이유로 뽑는다. <br/>
이처럼 자신의 성장 가능성에 많은 관심을 기울이는 사람들이 늘어나고 있다.<br/>
2030 대다수의 자기 개발에 대한 관심 증가와 더불어 IT 업계 특성상 업계 종사자들의 지속적인 성장에 대한 니즈를 충족시킬 수 있는 시장은 성장 가능성이 크다고 판단했다.<br/>
따라서 IT 관련 지식 서비스를 향유할 수 있는 플랫폼을 구성하게 되었다. 관리자의 승인 하에 사용자들은 누구나 튜터가 될 수 있고, 수업을 수강할 수 있다. 
<br/>
<br/>
### [프로젝트 기간]  📅
<Strong>1차</Strong>: 2023.01.06 - 2023.01.24 > **Maven+Mybatis** <br/>
<Strong>2차</Strong>: 2023.01.06 - 2023.01.24 > **SpringBoot+JPA+RestFul** <br/>
<br/>
<br/>

### [일정]  📅
1.   *1차 개발* 
-   23.01.06 - 23.01.13 : 기획 및 이벤트 스토밍 진행
-   23.01.14 - 23.01.16 : ERD 작성 및 DB 구현 
-   23.01.17 - 23.01.24 : 기능 개발 
-   23.01.25 : 1차 발표 

2.   *2차 개발*
-   23.02.20 - 23.02.22 : 프론트 작업 진행
-   23.02.23 - 23.02.27 : 1차 개발, 미구현 기능 작업 
-   23.02.28 : 파일 병합
-   23.03.01 - 23.03.07 : 2차 개발,  추가 기능 구현
-   23.03.08 - 23.03.12 : 프론트 및 기능 구현 마무리
-   23.03.13 : 파일 병합 및 발표 준비
-   23.03.14 : 2차 발표 
<br/>
<br/>
<h3> [적용기술]  📌  </h3>

<table>
  <tr><td>Language</td><td>JAVA SE-11, JavaScript</td>
  </tr>
  <tr><td>Server</td><td>Tomcat 9.0.71</td>
  </tr>
  <tr><td>Framework</td><td>SpringBoot 2.7.8</td>
  </tr>
  <tr><td>Library</td><td>JUnit, Jackson, json-simple, lombok, commons-fileupload, thumbnailator, modelmapper, log4jdbc, spring-boot-starter-mail, spring-boot-starter-websocket, JQuery, summerNote
</td>
  </tr>
  <tr><td>OS</td><td>Windows OS, Mac OS</td>
  </tr>
  <tr><td>Database</td><td>Oracle 11g Express Edition</td>
  </tr>
  <tr><td>IDE</td><td>Eclipse IDE 2022-12, Visual Studio code</td>
  </tr>
  <tr><td style="width: 150px">협업관리 툴</td><td>Notion, Google Drive, GitHub</td>
  </tr>
</table>
<br/>
<br/>
   
   <h3>[ERD]</h3>
<img src="https://github.com/daisyy0000/DEVELOPER-Back/blob/feature-sr/back/img/erd.png">
<br/>
<br/>
<h3> [주요 기능] 🔥 </h3>

<strong>1.   수업 </strong>
-   관리자 승인 하에, 회원들은 누구나 수업 제공자(이하 튜터) 가능
-   튜터는 무료 수업, 유료 수업을 제공. 단, 유료 수업 등록 시에는 플랫폼에 일정 금액을 결제 필요
-   튜터는 수업을 신청한 튜티를 승인 및 거절 가능
-   튜터는 완료된 수업을 수강한 튜티에 대한 후기작성 가능
-   수업 참여자(이하 튜티)는 자신이 원하는 수업을 튜터의 승인 하에 수강 가능
-   튜티는 완료된 수업에 대한 후기 작성 가능

<strong>2.   스터디카페</strong>
-   관리자 승인 하에, 호스트로 가입 가능
-   호스트는 한 개의 스터디 카페를 등록 가능
-   호스트는 등록한 스터디 카페의 오픈 시간/마감 시간을 설정할 수 있고, 해당 시간 내에만 예약이 가능
-   호스트는 본인이 원하는 시간의 예약막기 가능. 
-   회원은 원하는 스터디 카페를 예약 가능 
-   회원은 방문한 스터디 카페의 후기 작성 가능

<strong>3.   커뮤니티 </strong>
- 사용자는 원하는 조건에 따라 커뮤니티의 글을 검색하거나 정렬 가능
- 글 CRUD
- 댓글 CRUD

<strong>4. 채팅</strong>
- 튜터와 튜티사이에 채팅으로 대화 가능
<br/>
<br/>
<h3>😊페이지 구현😊</h3>
<img src="https://github.com/daisyy0000/DEVELOPER-Back/blob/feature-sr/back/img/page.png">
