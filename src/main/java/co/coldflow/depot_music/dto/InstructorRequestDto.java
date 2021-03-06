package co.coldflow.depot_music.dto;

import java.util.Objects;

public class InstructorRequestDto {
        private String nickName;
        private String realName;
        private String tel;
        private String memo;
        private String profileInfo;
        private String username;
        private String password;

        public InstructorRequestDto(String nickName, String realName, String tel, String memo, String profileInfo, String username, String password) {
                this.nickName = nickName;
                this.realName = realName;
                this.tel = tel;
                this.memo = memo;
                this.profileInfo = profileInfo;
                this.username = username;
                this.password = password;
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

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        @Override
        public String toString() {
                return "InstructorRequestDto{" +
                        "nickName='" + nickName + '\'' +
                        ", realName='" + realName + '\'' +
                        ", tel='" + tel + '\'' +
                        ", memo='" + memo + '\'' +
                        ", profileInfo='" + profileInfo + '\'' +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                InstructorRequestDto that = (InstructorRequestDto) o;
                return Objects.equals(nickName, that.nickName);
        }

        @Override
        public int hashCode() {
                return Objects.hash(nickName);
        }
}
