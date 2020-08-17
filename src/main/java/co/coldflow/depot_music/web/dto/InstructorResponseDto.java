package co.coldflow.depot_music.web.dto;


import co.coldflow.depot_music.entity.Instructor;
import org.springframework.web.multipart.MultipartFile;

public class InstructorResponseDto {
        private Long id;
        private String nickName;
        private String realName;
        private String tel;
        private String memo;
        private String profileInfo;
        private String username;
        private String portraitFileName;
        private String fileUrl;

        public InstructorResponseDto(Instructor instructor) {
                this.id = instructor.getId();
                this.nickName = instructor.getNickName();
                this.realName = instructor.getRealName();
                this.tel = instructor.getTel();
                this.memo = instructor.getMemo();
                this.profileInfo = instructor.getProfileInfo();
                this.username = instructor.getUsername();
                this.portraitFileName = instructor.getFileName ();
        }

        @Override
        public String toString() {
                return "InstructorResponseDto{" +
                        "id='" + id + '\'' +
                        ", nickName='" + nickName + '\'' +
                        ", realName='" + realName + '\'' +
                        ", tel='" + tel + '\'' +
                        ", memo='" + memo + '\'' +
                        ", profileInfo='" + profileInfo + '\'' +
                        ", username='" + username + '\'' +
                        ", portraitFileName='" + portraitFileName + '\'' +
                        ", fileUrl='" + fileUrl + '\'' +
                        '}';
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNickName() {
                return nickName;
        }

        public void setNickName(String nickName) {
                this.nickName = nickName;
        }

        public String getRealName() {
                return realName;
        }

        public void setRealName(String realName) {
                this.realName = realName;
        }

        public String getTel() {
                return tel;
        }

        public void setTel(String tel) {
                this.tel = tel;
        }

        public String getMemo() {
                return memo;
        }

        public void setMemo(String memo) {
                this.memo = memo;
        }

        public String getProfileInfo() {
                return profileInfo;
        }

        public void setProfileInfo(String profileInfo) {
                this.profileInfo = profileInfo;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPortraitFileName() {
                return portraitFileName;
        }

        public void setPortraitFileName(String portraitFileName) {
                this.portraitFileName = portraitFileName;
        }

        public String getFileUrl() {
                return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
        }
}
