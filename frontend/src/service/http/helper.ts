import {AxiosError} from "axios";
import {ERROR_STATUS} from "@/config";

/**
 * 处理请求结果
 * 状态码为200-300之间的
 * @param error
 * @param data
 */
export  function handleResult<T = any>(error: Service.RequestError | null, data: any) {
    if (error) {
        const fail: Service.FailedResult = {
            error,
            data: null
        };
        // @ts-ignore
        window.$message.error(error.msg);
        return fail;
    }
    const success: Service.SuccessResult<T> = {
        error: null,
        data
    };
    return success;
}

/**
 * 处理请求不成功的错误
 *
 * @param error
 */
export function handelAxiosError(error: AxiosError) {
    const {status} = error.response;
    const requestError: Service.RequestError = {
        type: 'http',
        code: status??'error',
        msg: ERROR_STATUS[status] || '未知错误'
    };
    return handleResult(requestError,null);
}

export function handelBackendError(result :Record<string, any>,config:Service.ResultConfig) {
    const {codeKey, msgKey} = config;
    const requestError: Service.RequestError = {
        type: 'backend',
        code: result[codeKey],
        msg: result[msgKey] || '未知错误'
    };
    return handleResult(requestError,null);
}