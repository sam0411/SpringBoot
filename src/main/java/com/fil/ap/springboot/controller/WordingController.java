package com.fil.ap.springboot.controller;

import com.fil.ap.springboot.constants.Constants;
import com.fil.ap.springboot.exception.SystemException;
import com.fil.ap.springboot.service.output.WordingOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(value = "/wording")
public class WordingController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordingController.class);

    @RequestMapping(value = "chinese", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public WordingOutput getChineseWording(
            @RequestHeader(required = false, value = Constants.PARAM_CONSUME) String consumer
    ) throws SystemException {

        LOGGER.info("Entry = WordingController -> getChineseWording, Consumer = " + consumer);

        WordingOutput result = new WordingOutput();
        result.setContent("富達基金（香港）有限公司2022。版權所有，不得轉載。");

        return result;
    }

    @RequestMapping(value = "chinese", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public WordingOutput postChineseWording(
            @RequestHeader(required = false, value = Constants.PARAM_CONSUME) String consumer
    ) throws SystemException {

        LOGGER.info("Entry = WordingController -> postChineseWording, Consumer = " + consumer);

        WordingOutput result = new WordingOutput();
        result.setContent("富達基金（香港）有限公司2022。版權所有，不得轉載。");

        return result;
    }
}
