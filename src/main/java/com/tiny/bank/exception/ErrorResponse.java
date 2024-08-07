package com.tiny.bank.exception;

import java.util.Set;

public record ErrorResponse (Set<String> messages) {}
