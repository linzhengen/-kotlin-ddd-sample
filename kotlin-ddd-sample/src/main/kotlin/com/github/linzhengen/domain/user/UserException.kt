package com.github.linzhengen.domain.user

import java.lang.RuntimeException

class UserNotFoundException(message: String = "user not found"): RuntimeException(message)
