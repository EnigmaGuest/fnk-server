package fun.isite.service.core.system.consts;

/**
 * 缓存key
 *
 * @author Enigma
 */
public class RoleCacheKey {

    /**
     * 用户角色缓存key
     */
    public static final String ROLE_USER = "role:user:";

    /**
     * 角色权限缓存key
     */
    public static final String ROLE_PERMISSION = "role:permission:";

    // saToken session key
    public static final String ROLE_SESSION_ID = "role-key-";
    public static final String ROLE_SESSION_KEY = "Role_List";
    public static final String PERMISSION_SESSION_KEY = "Permission_List";

    // saToken 所有的角色缓存key 如果发生权限变更，需要清除这个缓存
    public static final String AUTH_CUSTOM_SESSION_KEY = "Authorization:custom:session:role-key-";

}
