package com.igorivkin.website.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class UserServiceTests {

    @Autowired
    private lateinit var userService: UserService

    /*
    @Test
    fun creationUserTest() {
        val userDto = UserDto(
            username = "admin@example.com",
            password = "123",
            title = "Admin",
            role = UserRole.ROLE_ADMINISTRATOR
        )
        val createdUser: User = userService.createFromDto(userDto)
        assertNotNull(createdUser.id)
        assertNotNull(createdUser.createdAt)
        assertEquals(createdUser.title, "Admin")
    }*/

}