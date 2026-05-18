# 后端接口文档

## 基础信息

- **Base URL**: `http://localhost:8080`
- **请求格式**: JSON (`Content-Type: application/json`)
- **文件上传**: `multipart/form-data`
- **认证方式**: JWT Token（请求头 `token` 字段）

---

## 通用响应格式

### 成功响应
```json
{
  "code": 1,
  "msg": "success",
  "data": { ... }
}
```

### 失败响应
```json
{
  "code": 0,
  "msg": "错误信息",
  "data": null
}
```

### 分页响应 (data 字段)
```json
{
  "total": 100,
  "rows": [ ... ]
}
```

---

## 一、登录模块

### 1.1 登录

```
POST /login
```

**请求体**:
```json
{
  "username": "string",
  "password": "string"
}
```

**成功响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 1,
    "username": "admin",
    "name": "管理员",
    "token": "eyJhbGciOiJIUzI1NiJ9..."
  }
}
```

**失败响应**:
```json
{
  "code": 0,
  "msg": "用户名或密码错误",
  "data": null
}
```

**说明**: 登录成功后将 `token` 保存到前端，后续所有请求需在请求头中携带 `token`。

---

## 二、部门管理模块

### 2.1 查询全部部门

```
GET /depts
```

**请求头**: `token: <jwt_token>`

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "name": "研发部",
      "createTime": "2026-05-16T10:00:00",
      "updateTime": "2026-05-16T10:00:00"
    }
  ]
}
```

### 2.2 根据 ID 查询部门

```
GET /depts/{id}
```

**请求头**: `token: <jwt_token>`

**路径参数**: `id` - 部门 ID（整数）

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 1,
    "name": "研发部",
    "createTime": "2026-05-16T10:00:00",
    "updateTime": "2026-05-16T10:00:00"
  }
}
```

### 2.3 新增部门

```
POST /depts
```

**请求头**: `token: <jwt_token>`

**请求体**:
```json
{
  "name": "新部门名称"
}
```

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 2.4 修改部门

```
PUT /depts
```

**请求头**: `token: <jwt_token>`

**请求体**:
```json
{
  "id": 1,
  "name": "修改后的名称"
}
```

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 2.5 删除部门

```
DELETE /depts?id={id}
```

**请求头**: `token: <jwt_token>`

**Query 参数**: `id` - 部门 ID（整数）

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

## 三、员工管理模块

### 3.1 分页查询员工

```
GET /emps
```

**请求头**: `token: <jwt_token>`

**Query 参数**:

| 参数 | 类型 | 必填 | 默认值 | 说明 |
|------|------|------|--------|------|
| page | Integer | 否 | 1 | 页码 |
| pageSize | Integer | 否 | 10 | 每页条数 |
| name | String | 否 | - | 按部门名称模糊搜索 |
| gender | Integer | 否 | - | 性别筛选：1=男, 2=女 |
| begin | String | 否 | - | 开始日期 yyyy-MM-dd |
| end | String | 否 | - | 结束日期 yyyy-MM-dd |

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "total": 100,
    "rows": [
      {
        "id": 1,
        "username": "zhangsan",
        "password": "123456",
        "name": "张三",
        "gender": 1,
        "phone": "13800138000",
        "job": 1,
        "salary": 10000,
        "image": "http://xxx.com/avatar.jpg",
        "entryDate": "2026-01-01",
        "deptId": 1,
        "createTime": "2026-05-16T10:00:00",
        "updateTime": "2026-05-16T10:00:00",
        "deptName": "研发部",
        "exprList": null
      }
    ]
  }
}
```

**注意**: 列表查询时 `exprList` 始终为 `null`，详情接口才会返回工作经历。

### 3.2 根据 ID 查询员工详情

```
GET /emps/{id}
```

**请求头**: `token: <jwt_token>`

**路径参数**: `id` - 员工 ID

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 1,
    "username": "zhangsan",
    "password": "123456",
    "name": "张三",
    "gender": 1,
    "phone": "13800138000",
    "job": 1,
    "salary": 10000,
    "image": "http://xxx.com/avatar.jpg",
    "entryDate": "2026-01-01",
    "deptId": 1,
    "createTime": "2026-05-16T10:00:00",
    "updateTime": "2026-05-16T10:00:00",
    "deptName": "研发部",
    "exprList": [
      {
        "empId": 1,
        "begin": "2020-01-01",
        "end": "2022-12-31",
        "company": "某公司",
        "job": "工程师"
      }
    ]
  }
}
```

### 3.3 新增员工

```
POST /emps
```

**请求头**: `token: <jwt_token>`

**请求体**:
```json
{
  "username": "zhangsan",
  "password": "123456",
  "name": "张三",
  "gender": 1,
  "phone": "13800138000",
  "job": 1,
  "salary": 10000,
  "image": "http://xxx.com/avatar.jpg",
  "entryDate": "2026-01-01",
  "deptId": 1,
  "exprList": [
    {
      "begin": "2020-01-01",
      "end": "2022-12-31",
      "company": "某公司",
      "job": "工程师"
    }
  ]
}
```

**字段说明**:

| 字段 | 类型 | 必填 | 说明 |
|------|------|------|------|
| username | String | 是 | 用户名，不可重复 |
| password | String | 是 | 密码 |
| name | String | 是 | 姓名 |
| gender | Integer | 否 | 性别：1=男, 2=女 |
| phone | String | 否 | 手机号 |
| job | Integer | 否 | 职位：1=father, 2=mother, 3=brother, 4=sister, 5=grandparent, 其他=other |
| salary | Integer | 否 | 薪资 |
| image | String | 否 | 头像 URL |
| entryDate | String | 否 | 入职日期 yyyy-MM-dd |
| deptId | Integer | 否 | 部门 ID |
| exprList | Array | 否 | 工作经历列表 |

**exprList 子字段**:

| 字段 | 类型 | 说明 |
|------|------|------|
| begin | String | 开始日期 yyyy-MM-dd |
| end | String | 结束日期 yyyy-MM-dd |
| company | String | 公司名称 |
| job | String | 职位 |

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

**失败示例**（用户名重复）:
```json
{
  "code": 0,
  "msg": "zhangsan已存在",
  "data": null
}
```

### 3.4 修改员工

```
PUT /emps
```

**请求头**: `token: <jwt_token>`

**请求体**: 与新增员工相同（包含完整字段）

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

### 3.5 批量删除员工

```
DELETE /emps?ids=1,2,3
```

**请求头**: `token: <jwt_token>`

**Query 参数**: `ids` - 员工 ID 列表（逗号分隔）

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

## 四、统计报表模块

### 4.1 员工职位统计

```
GET /empJobData
```

**请求头**: `token: <jwt_token>`

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "jobList": ["father", "mother", "brother", "sister", "grandparent", "other"],
    "dataList": [10, 5, 8, 3, 2, 1]
  }
}
```

**职位映射**: 1=father, 2=mother, 3=brother, 4=sister, 5=grandparent, 其他=other

### 4.2 员工性别统计

```
GET /empGenderData
```

**请求头**: `token: <jwt_token>`

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "genderList": ["男性", "女性"],
    "dataList": [30, 20]
  }
}
```

**说明**: `genderList` 和 `dataList` 按索引一一对应。

---

## 五、文件上传模块

### 5.1 上传文件

```
POST /upload
```

**请求头**: `token: <jwt_token>`

**请求格式**: `multipart/form-data`

**表单字段**:

| 字段 | 类型 | 说明 |
|------|------|------|
| name | String | 名称 |
| age | Integer | 年龄 |
| file | File | 上传的文件 |

**响应**:
```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

## 附录

### A. 请求头汇总

所有受保护的接口需要在请求头中携带 JWT Token：

```
token: eyJhbGciOiJIUzI1NiJ9...
```

### B. 错误码汇总

| code | 含义 |
|------|------|
| 1 | 成功 |
| 0 | 通用失败（msg 包含具体错误信息） |

### C. 特殊错误信息

| 错误信息 | 触发场景 |
|----------|----------|
| 用户名或密码错误 | 登录验证失败 |
| xxx已存在 | 数据库唯一键冲突（如用户名重复） |
| 出错啦，请联系管理员~ | 服务端未知异常 |

### D. 当前状态说明

- **认证拦截器**: 当前代码中 `WebConfig` 配置被注释，拦截器未启用。开发阶段所有接口无需 token 可直接访问。如需启用认证，取消 `WebConfig` 类的 `@Configuration` 注释即可。
- **Token 有效期**: 12 小时
