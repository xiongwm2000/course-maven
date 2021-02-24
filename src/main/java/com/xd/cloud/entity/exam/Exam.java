/*
 * Copyright (c) 2014, 2015, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xd.cloud.entity.exam;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;

/**
 * 试卷
 * 
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_exam")
public class Exam extends BaseEntity{

	private String name;
}
