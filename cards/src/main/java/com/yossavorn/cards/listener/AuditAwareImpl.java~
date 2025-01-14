package com.yossavorn.accounts.listener;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    @Nonnull
    public Optional<String> getCurrentAuditor() {
        return Optional.of("yossavorn");
    }
}
