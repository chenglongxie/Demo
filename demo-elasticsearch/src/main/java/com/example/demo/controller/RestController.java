package com.example.demo.controller;

import com.example.demo.data.Document;
import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/v1/")
public class RestController {

    private static ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();

    /**
     * 保存文档
     * @param _index
     * @param document
     * @return
     */
    @RequestMapping(value = "add/{_index}")
    @ResponseBody
    public String addDocument(@PathVariable String _index, @RequestBody Document document) {
        return clientUtil.addDocument(_index, "_doc", document);
    }

    /**
     * 获取文档
     * @param _index
     * @param _id
     * @return
     */
    @RequestMapping(value = "get/{_index}/{_id}")
    @ResponseBody
    public Document getDocument(@PathVariable String _index, @PathVariable String _id) {
        return clientUtil.getDocument(_index, "_doc", _id, Document.class);
    }

    /**
     * 删除文档
     * @param _index
     * @param _id
     * @return
     */
    @RequestMapping(value = "del/{_index}/{_id}")
    @ResponseBody
    public String delDocument(@PathVariable String _index, @PathVariable String _id) {
        return clientUtil.deleteDocument(_index, "_doc", _id);
    }


}
