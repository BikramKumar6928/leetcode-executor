package com.github.bikramkumar6928.leetcodeExecutor.beans;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdatedInputAndParameter {
    private Object parameter;
    private String updatedInput;
}
