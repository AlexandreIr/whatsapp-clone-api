package com.aftech.wppclone.user;

import com.aftech.wppclone.chat.Chat;
import com.aftech.wppclone.common.BaseAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
@NamedQuery(name = UserConstants.FIND_USER_BY_EMAIL,
        query = "SELECT u FROM User u WHERE u.email = :email")
@NamedQuery(name = UserConstants.FIND_ALL_USERS_EXCEPT_SELF,
            query = "SELECT u FROM User u WHERE u.id != :publicId")
@NamedQuery(name = UserConstants.FIND_USER_BY_PUBLIC_ID,
            query = "SELECT u FROM User u WHERE u.id = :publicID")
public class User extends BaseAuditingEntity {

    private static final long LAST_ACTIVE_INTERVAL = 5;
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime lastSeen;

    @OneToMany(mappedBy = "sender")
    private List<Chat> chatAsSender;
    @OneToMany(mappedBy = "recipient")
    private List<Chat> chatAsRecipient;

    @Transient
    public boolean isUserOnline(){
        return lastSeen!=null && lastSeen.isAfter(LocalDateTime.now().plusMinutes(LAST_ACTIVE_INTERVAL));
    }
}
