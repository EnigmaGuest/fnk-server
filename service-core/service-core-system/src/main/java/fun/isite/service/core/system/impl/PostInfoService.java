package fun.isite.service.core.system.impl;

import fun.isite.service.core.system.entity.PostInfo;
import fun.isite.service.core.system.mapper.PostInfoMapper;
import fun.isite.service.core.system.service.IPostInfoService;
import fun.isite.service.common.db.impl.BaseService;
import org.springframework.stereotype.Service;

/**
* 岗位信息 服务实现层
*
* @author Enigma
* @since 2023-12-18
*/
@Service
public class PostInfoService extends BaseService<PostInfoMapper, PostInfo> implements IPostInfoService {


}
