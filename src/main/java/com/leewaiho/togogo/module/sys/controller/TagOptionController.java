package com.leewaiho.togogo.module.sys.controller;

import com.leewaiho.togogo.common.base.controller.BaseController;
import com.leewaiho.togogo.module.sys.model.tag.TBTagOption;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.leewaiho.togogo.common.base.controller.BaseController.BASE_PATH;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/23
 * Project togogo-shixun
 */
@RestController
@RequestMapping(BASE_PATH + "/tagOptions")
public class TagOptionController extends BaseController<TBTagOption> {
}
