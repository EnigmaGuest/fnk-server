package ${package.Controller};

import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import com.zhz.hotel.common.bean.dto.SplitPageDTO;
import com.zhz.hotel.common.bean.http.RestResponse;
import com.zhz.hotel.core.basic.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
<#if restControllerStyle>
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

import java.util.List;

/**
* ${table.comment!} 控制层
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen?replace("-","/")?replace("_","/")}<#else>${table.entityPath?replace("-","/")?replace("_","/")}</#if>")
@Api(tags = {"${table.comment!}"})
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

	@Autowired
    private ${table.serviceName} service;

    @GetMapping
    @ApiOperation("${table.comment!}列表")
    public RestResponse<PageVO<${entity}>> list(SplitPageDTO page,${entity} params) {
        return RestResponse.ok(this.service.basicPage(page, ${entity}::getCreatedAt, (w) -> {
            //TODO 此处应去除不需要的查询条件
            <#list table.fields as field>
                <#if field.propertyType == "boolean">
                    <#assign getprefix="is"/>
                <#else>
                    <#assign getprefix="get"/>
                </#if>
                <#if field.propertyType == "String" || field.propertyType == "Long" || field.propertyType == "Integer" || field.propertyType == "Float" || field.propertyType == "Double">
            w.like(params.${getprefix}${field.capitalName}()!=null, ${entity}::${getprefix}${field.capitalName}, params.${getprefix}${field.capitalName}());
                <#elseif field.propertyType == "List" || field.propertyType == "Set" || field.propertyType == "Map">
            w.in(params.${getprefix}${field.capitalName}()!=null, ${entity}::${getprefix}${field.capitalName}, params.${getprefix}${field.capitalName}());
                <#else>
            w.eq(params.${getprefix}${field.capitalName}()!=null, ${entity}::${getprefix}${field.capitalName}, params.${getprefix}${field.capitalName}());
                </#if>
            </#list>
        }));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取指定ID${table.comment!}的详情")
    public RestResponse<${entity}> detail(@PathVariable Long id) {
        return RestResponse.ok(this.service.detail(id));
    }

    @PostMapping
    @ApiOperation("创建${table.comment!}")
    public RestResponse<${entity}> create(@RequestBody @Validated ${entity} req) {
        return RestResponse.ok(this.service.create(req));
    }

    @PutMapping("/{id}")
    @ApiOperation("更新指定ID的${table.comment!}")
    public RestResponse<${entity}> update(@PathVariable Long id, @RequestBody @Validated ${entity} req) {
        return RestResponse.ok(this.service.update(id, req));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除指定ID的${table.comment!}")
    public RestResponse<Void> remove(@PathVariable Long id) {
        this.service.removeSingle(id);
        return RestResponse.ok();
    }

    @DeleteMapping
    @ApiOperation("批量删除指定ID的${table.comment!}")
    public RestResponse<Void> remove(@RequestParam("id-list") List<Long> idList) {
        this.service.remove(idList);
        return RestResponse.ok();
    }

}
